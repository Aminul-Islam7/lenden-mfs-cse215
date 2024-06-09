package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PayBillGasController implements Payable {

    @FXML
    private TextField nameField, idField, amountField, pinField;

    @FXML
    private Label errorLabel;

    @FXML
    @Override
    public void handlePayBill() {

    }
}
