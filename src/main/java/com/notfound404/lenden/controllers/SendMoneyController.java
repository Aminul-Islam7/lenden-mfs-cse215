package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionLimit;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class SendMoneyController implements Payable {

  @FXML
  private Label errorLabel;

  @FXML
  private TextField phoneField, amountField, referenceField, pinField;

  @FXML
  @Override
  public void processOutgoingTransaction() {
    UserService userService = new UserService();
    TransactionService transactionService = new TransactionService();

    if (phoneField.getText().isEmpty() || amountField.getText().isEmpty() || referenceField.getText().isEmpty()
        || pinField.getText().isEmpty()) {
      errorLabel.setText("Please fill in all the fields");
      return;
    }

    if (!phoneField.getText().matches("01[2-9]\\d{8}")) {
      errorLabel.setText("Invalid Phone Number");
      return;
    }

    if (!amountField.getText().matches("[0-9]+(\\.\\d+)?") || Double.parseDouble(amountField.getText()) <= 0.0) {
      errorLabel.setText("Invalid Amount");
      return;
    }

    double amountSpent = transactionService.getSpentAmount(userService.getCurrentUser(), "Send Money");
    if (amountSpent + Double.parseDouble(amountField.getText()) > TransactionLimit.SEND_MONEY.getLimit()) {
      errorLabel.setText("You have reached your daily limit for sending money");
      return;
    }

    if (Double.parseDouble(amountField.getText()) > userService.getCurrentUser().getBalance()) {
      errorLabel.setText("Insufficient Balance");
      return;
    }

    int currentUserPin = userService.getCurrentUser().getPin();
    if (!pinField.getText().equals(String.valueOf(currentUserPin))) {
      errorLabel.setText("Invalid PIN");
      return;
    }

    TransactionInfo destination = new TransactionInfo("Sent to", phoneField.getText());
    double charge = 5.0;
    double amount = Double.parseDouble(amountField.getText()) + charge;

    transactionService.addTransaction(userService.getCurrentUser(), TransactionType.SEND_MONEY,
        destination, amount, charge, new TransactionInfo("Reference", referenceField.getText()));

    userService.deductBalance(userService.getCurrentUser(), amount);

    SceneController.setScene("SendMoneySuccess.fxml", "Send Money");
  }

}
