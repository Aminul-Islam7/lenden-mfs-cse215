package com.notfound404.lenden.controllers;

public class PayBillController {

    public void visitPayBillElectricity() {
        SceneController.setScene("PayBillElectricity.fxml", "Pay Bill - Electricity");
    }

    public void visitPayBillGas() {
        SceneController.setScene("PayBillGas.fxml", "Pay Bill - Gas");
    }

    public void visitPayBillWater() {
        SceneController.setScene("PayBillWater.fxml", "Pay Bill - Water");
    }

    public void visitPayBillInternet() {
        SceneController.setScene("PayBillInternet.fxml", "Pay Bill - Internet");
    }

}
