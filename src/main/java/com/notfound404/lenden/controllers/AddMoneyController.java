package com.notfound404.lenden.controllers;

public class AddMoneyController {

  public void visitAMFromBank() {
    SceneController.setScene("AMFromBank.fxml", "Add Money from Bank",null);
  }

  public void visitAMFromCard() {
    SceneController.setScene("AMFromCard.fxml", "Add Money from Card",null);
  }

}
