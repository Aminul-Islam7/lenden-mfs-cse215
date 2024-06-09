package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionLimit;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AMFromBankController extends AddMoney {

  @FXML
  private ChoiceBox<String> bankChoiceBox;

  @FXML
  private Label otpLabel, errorLabel;

  @FXML
  private TextField nameField, numberField, otpField, amountField, pinField;

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

  @FXML
  private void sendOtp() {
    otpLabel.setText("An OTP has been sent to the phone number\nthat is registered to your Bank Account");
    otpLabel.setStyle("-fx-text-fill: green;");
  }

  @FXML
  @Override
  public void handleAddMoney() {
    UserService userService = new UserService();
    TransactionService transactionService = new TransactionService();

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

    if (!amountField.getText().matches("[0-9]+(\\.\\d+)?") || Double.parseDouble(amountField.getText()) <= 0.0) {
      errorLabel.setText("Invalid Amount");
      return;
    }

    double amountSpent = transactionService.getSpentAmount(userService.getCurrentUser(), "Add Money");
    if (amountSpent + Double.parseDouble(amountField.getText()) > TransactionLimit.ADD_MONEY.getLimit()) {
      errorLabel.setText("You have reached your daily limit for adding money");
      return;
    }

    int currentUserPin = userService.getCurrentUser().getPin();
    if (!pinField.getText().equals(String.valueOf(currentUserPin))) {
      errorLabel.setText("Invalid PIN");
      return;
    }

    TransactionInfo destination = new TransactionInfo("Bank", bankChoiceBox.getValue().toString());
    TransactionInfo reference = new TransactionInfo("Account Number", numberField.getText());
    double charge = 0.0;
    double amount = Double.parseDouble(amountField.getText()) + charge;

    transactionService.addTransaction(userService.getCurrentUser(), TransactionType.ADD_MONEY_BANK,
        destination, amount, charge,
        reference);

    userService.addBalance(userService.getCurrentUser(), Double.parseDouble(amountField.getText()));

    SceneController.setScene("AMFromBankSuccess.fxml", "Add Money from Bank");

  }

}