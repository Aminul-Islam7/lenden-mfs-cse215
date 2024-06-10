package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionLimit;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AMFromCardController extends AddMoney {

  @FXML
  private Label errorLabel;

  @FXML
  private TextField nameField, numberField, monthField, yrField, securityCodeField, postalCodeField, amountField;

  @FXML
  private PasswordField pinField;

  @FXML
  @Override
  public void handleAddMoney() {
    UserService userService = new UserService();
    TransactionService transactionService = new TransactionService();

    if (nameField.getText().isEmpty() || numberField.getText().isEmpty() || monthField.getText().isEmpty()
        || yrField.getText().isEmpty() || securityCodeField.getText().isEmpty() || postalCodeField.getText().isEmpty()
        || pinField.getText().isEmpty()) {
      errorLabel.setText("Please fill in all the fields");
      return;
    }

    if (!numberField.getText().matches("[0-9]+")) {
      errorLabel.setText("Invalid Card Number");
      return;
    }

    if (!monthField.getText().matches("^[1-9]|1[1-2]$")) {
      errorLabel.setText("Invalid Month");
      return;
    }

    if (!yrField.getText().matches("^\\d{2}$")) {
      errorLabel.setText("Invalid Year");
      return;
    }

    if (!securityCodeField.getText().matches("[0-9]+")) {
      errorLabel.setText("Invalid Security Code");
      return;
    }

    if (!postalCodeField.getText().matches("[0-9]+")) {
      errorLabel.setText("Invalid Postal Code");
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

    TransactionInfo destination = new TransactionInfo("Card", "Debit/Credit/Prepaid Card");
    TransactionInfo reference = new TransactionInfo("Card Number", numberField.getText());
    double charge = 0.0;
    double amount = Double.parseDouble(amountField.getText()) + charge;

    transactionService.addTransaction(userService.getCurrentUser(), TransactionType.ADD_MONEY_CARD,
        destination, amount, 0.0, reference);

    userService.addBalance(userService.getCurrentUser(), Double.parseDouble(amountField.getText()));

    SceneController.setScene("AMFromCardSuccess.fxml", "Add Money from Card");
  }

}
