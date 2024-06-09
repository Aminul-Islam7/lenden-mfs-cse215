package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;

public class PayBillController {

    @FXML
    public void visitPayBillElectricity() {
        SceneController.setScene("PayBillElectricity.fxml", "Pay Bill - Electricity");
    }

    @FXML
    public void visitPayBillGas() {
        SceneController.setScene("PayBillGas.fxml", "Pay Bill - Gas");
    }

    @FXML
    public void visitPayBillWater() {
        SceneController.setScene("PayBillWater.fxml", "Pay Bill - Water");
    }

    @FXML
    public void visitPayBillInternet() {
        SceneController.setScene("PayBillInternet.fxml", "Pay Bill - Internet");
    }

}
