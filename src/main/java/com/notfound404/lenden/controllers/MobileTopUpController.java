package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MobileTopUpController implements Payable{

    @FXML
  private Label errorLabel;

  @FXML
  private TextField phoneField, amountField;

  @FXML
  private PasswordField pinField;

  @FXML
  public void processOutgoingTransaction() {

    if (phoneField.getText().isEmpty() || amountField.getText().isEmpty() || pinField.getText().isEmpty()) {
      errorLabel.setText("Please fill in all the fields");
      return;
    }

    if (!phoneField.getText().matches("01[2-9]\\d{8}")) {
      errorLabel.setText("Invalid Phone Number");
      return;
    }

    if (!amountField.getText().matches("[0-9]+(\\.\\d+)?") || Double.parseDouble(amountField.getText()) <= 0.0
        || Double.parseDouble(amountField.getText()) > 10000.0) {
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
    TransactionInfo destination = new TransactionInfo("Recharge Number", phoneField.getText());
    double amount = Double.parseDouble(amountField.getText());
    double charge = 0.0;
    transactionService.addTransaction(userService.getCurrentUser(), TransactionType.MOBILE_TOP_UP,
        destination, amount, charge, null);

        userService.deductBalance(userService.getCurrentUser(), amount);

    SceneController.setScene("MobileTopUpSuccess.fxml", "Mobile Top-Up Successfull");
}
}
