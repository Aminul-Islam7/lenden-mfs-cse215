package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PayBillController implements Payable {

    @FXML
    private TextField nameField, idField, amountField, pinField;

    @FXML
    private Label errorLabel;

    private String billType;

    @FXML
    public void visitPayBillElectricity() {
        SceneController.setScene("PayBillForm.fxml", "Pay Bill - Electricity");
    }

    @FXML
    public void visitPayBillGas() {
        SceneController.setScene("PayBillForm.fxml", "Pay Bill - Gas");
    }

    @FXML
    public void visitPayBillWater() {
        SceneController.setScene("PayBillForm.fxml", "Pay Bill - Water");
    }

    @FXML
    public void visitPayBillInternet() {
        SceneController.setScene("PayBillForm.fxml", "Pay Bill - Internet");
    }

    @FXML
    @Override
    public void processOutgoingTransaction() {

        if (nameField.getText().isEmpty() || idField.getText().isEmpty() || amountField.getText().isEmpty()
                || pinField.getText().isEmpty()) {
            errorLabel.setText("Please fill in all the fields.");
            return;
        }

        if (!idField.getText().matches("\\d{6}")) {
            errorLabel.setText("Invalid ID");
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
        TransactionInfo destination = new TransactionInfo("Provider", nameField.getText());
        double charge = 5.0;
        double amount = Double.parseDouble(amountField.getText()) + charge;

        billType = SceneController.getMainLayoutController().getSceneLabel().getText().split(" - ")[1];

        transactionService.addTransaction(userService.getCurrentUser(), TransactionType.PAY_BILL,
                destination, amount, charge, new TransactionInfo("Customer ID", idField.getText()));

        userService.deductBalance(userService.getCurrentUser(), amount);

        SceneController.setScene("PayBillSuccess.fxml", "Pay Bill - " + billType);
    }
}