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

    }
}
