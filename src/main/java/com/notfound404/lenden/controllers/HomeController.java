package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;

public class HomeController {

  @FXML
  private void visitAddMoney() {
    SceneController.setScene("AddMoney.fxml", "Add Money");
  }

  @FXML
  private void visitMakePayment() {
    SceneController.setScene("MakePayment.fxml", "Make Payment");
  }

  @FXML
  private void visitSendMoney() {
    SceneController.setScene("SendMoney.fxml", "Send Money");
  }

  @FXML
  private void visitMobileTopUp() {
    SceneController.setScene("MobileTopUp.fxml", "Mobile Top Up");
  }

  @FXML
  private void visitLendenToBank() {
    SceneController.setScene("LendenToBank.fxml", "Lenden To Bank");
  }

  @FXML
  private void visitWithdrawCash() {
    SceneController.setScene("WithdrawCash.fxml", "Withdraw Cash");
  }

  @FXML
  private void visitPayBill() {
    SceneController.setScene("PayBill.fxml", "Pay Bill");
  }

  @FXML
  private void visitBookTickets() {
    SceneController.setScene("BookTickets.fxml", "Book Tickets");
  }

  @FXML
  private void visitCalculators() {
    SceneController.setScene("Calculator.fxml", "Calculators");
  }

}
