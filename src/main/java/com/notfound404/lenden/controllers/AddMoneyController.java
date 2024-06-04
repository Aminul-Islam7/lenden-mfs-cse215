package com.notfound404.lenden.controllers;

public class AddMoneyController {

  public void visitAMFromCard() {
    SceneController.setScene("AMFromCard.fxml", "Add Money from Card");
  }

  public void visitAMFromCardSuccess() {
    SceneController.setScene("AMFromCardSuccess.fxml", "Add Money from Card");
  }

  public void visitAMFromBank() {
    SceneController.setScene("AMFromBank.fxml", "Add Money from Bank");
  }

  public void visitAMFromBankSuccess() {
    SceneController.setScene("AMFromBankSuccess.fxml", "Add Money from Bank");
  }

}
