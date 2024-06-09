package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MakePaymentController implements Payable {

    @FXML
    private TextField phoneNumberTextField, amountTextField, referenceTextField;

    @FXML
    private PasswordField pinPasswordField;

    @FXML
    private Label errorLabel;

    @FXML
    @Override
    public void processOutgoingTransaction() {

        UserService userService = new UserService();

        if (phoneNumberTextField.getText().isEmpty() || amountTextField.getText().isEmpty()
                || pinPasswordField.getText().isEmpty() || referenceTextField.getText().isEmpty()) {
            errorLabel.setText("Please fill in all the fields");
            return;
        }

        if (!phoneNumberTextField.getText().matches("01[2-9]\\d{8}")) {
            errorLabel.setText("Invalid Phone Number");
            return;
        }

        if (!amountTextField.getText().matches("[0-9]+(\\.\\d+)?")
                || Double.parseDouble(amountTextField.getText()) <= 0.0
                || Double.parseDouble(amountTextField.getText()) > 1000000.0) {
            errorLabel.setText("Invalid Amount");
            return;
        }

        if (Double.parseDouble(amountTextField.getText()) > userService.getCurrentUser().getBalance()) {
            errorLabel.setText("Insufficient Balance");
            return;
        }

        int currentUserPin = userService.getCurrentUser().getPin();
        if (!pinPasswordField.getText().equals(String.valueOf(currentUserPin))) {
            errorLabel.setText("Invalid PIN");
            return;
        }

        TransactionService transactionService = new TransactionService();
        TransactionInfo destination = new TransactionInfo("Merchant Number", phoneNumberTextField.getText());
        TransactionInfo reference = new TransactionInfo("Reference", referenceTextField.getText());
        double charge = 0.0;
        double amount = Double.parseDouble(amountTextField.getText()) + charge;
        transactionService.addTransaction(userService.getCurrentUser(), TransactionType.MAKE_PAYMENT,
                destination, amount, charge, reference);

        userService.deductBalance(userService.getCurrentUser(), amount);

        SceneController.setScene("MakePaymentSuccess.fxml", "Make Payment");

    }

}
