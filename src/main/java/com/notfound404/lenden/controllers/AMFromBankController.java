package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.Transaction;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AMFromBankController {

  @FXML
  private ChoiceBox<String> bankChoiceBox;

  @FXML
  private Label otpLabel, errorLabel;

  @FXML
  private TextField nameField, numberField, otpField, amountField, pinField;

  public static AMFromBankSuccessController amFromBankSuccessController;

  private String[] banks = {
      "AB Bank Limited",
      "Bangladesh Development Bank",
      "Dhaka Bank Limited",
      "Community Bank Bangladesh",
      "IFIC Bank PLC",
      "Sonali Bank",
      "Social Islami Bank PLC",
      "First Security Islami Bank PLC.",
      "Rajshahi Krishi Unnayan Bank",
      "Mutual Trust Bank Ltd.",
      "NRBC Bank PLC.",
      "Southeast Bank Limited",
      "Pubali Bank PLC",
      "The Premier Bank PLC",
      "Midland Bank Limited",
  };

  @FXML
  private void initialize() {
    bankChoiceBox.setValue("Select a Bank");
    bankChoiceBox.getItems().addAll(banks);
  }

  public static void setAMFromBankSuccessController(AMFromBankSuccessController controller) {
    amFromBankSuccessController = controller;
  }

  @FXML
  private void sendOtp() {
    otpLabel.setText("An OTP has been sent to the phone number\nthat is registered to your Bank Account");
    otpLabel.setStyle("-fx-text-fill: green;");
  }

  @FXML
  private void handleAddMoney() {

    if (bankChoiceBox.getValue().equals("Select a Bank")) {
      errorLabel.setText("Please select a Bank");
      return;
    }

    if (nameField.getText().isEmpty() || numberField.getText().isEmpty() || otpField.getText().isEmpty()
        || amountField.getText().isEmpty() || pinField.getText().isEmpty()) {
      errorLabel.setText("Please fill in all the fields");
      return;
    }

    if (!numberField.getText().matches("[0-9]+")) {
      errorLabel.setText("Invalid Account Number");
      return;
    }

    if (!otpField.getText().equals("1234")) {
      errorLabel.setText("Invalid OTP");
      return;
    }

    if (!amountField.getText().matches("[0-9]+(\\.\\d+)?") || Double.parseDouble(amountField.getText()) <= 0.0
        || Double.parseDouble(amountField.getText()) > 1000000.0) {
      errorLabel.setText("Invalid Amount");
      return;
    }

    UserService userService = new UserService();
    int currentUserPin = userService.getCurrentUser().getPin();
    if (!pinField.getText().equals(String.valueOf(currentUserPin))) {
      errorLabel.setText("Invalid PIN");
      return;
    }

    TransactionService transactionService = new TransactionService();
    transactionService.addTransaction(userService.getCurrentUser(), TransactionType.ADD_MONEY,
        bankChoiceBox.getValue().toString(), Double.parseDouble(amountField.getText()), 0.0,
        numberField.getText());

    userService.addBalance(userService.getCurrentUser(), Double.parseDouble(amountField.getText()));

    SceneController.setScene("AMFromBankSuccess.fxml", "Add Money from Bank");

  }

}
