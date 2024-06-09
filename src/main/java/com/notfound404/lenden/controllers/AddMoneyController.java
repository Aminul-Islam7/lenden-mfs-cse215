package com.notfound404.lenden.controllers;

public abstract class AddMoneyController {

  public void visitAMFromBank() {
    SceneController.setScene("AMFromBank.fxml", "Add Money from Bank");
  }

  public void visitAMFromCard() {
    SceneController.setScene("AMFromCard.fxml", "Add Money from Card");
  }

  public abstract void handleAddMoney();

}
