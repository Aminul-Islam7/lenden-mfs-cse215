package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;

public class HomeController {

  @FXML
  private void visitAddMoney() {
    SceneController.setScene("AddMoney.fxml", "Add Money",null);
  }

  @FXML
  private void visitMakePayment() {
    SceneController.setScene("MakePayment.fxml", "Make Payment",null);
  }

  @FXML
  private void visitSendMoney() {
    SceneController.setScene("SendMoney.fxml", "Send Money",null);
  }

  @FXML
  private void visitMobileTopUp() {
    SceneController.setScene("MobileTopUp.fxml", "Mobile Top Up",null);
  }

  @FXML
  private void visitLendenToBank() {
    SceneController.setScene("LendenToBank.fxml", "Lenden To Bank",null);
  }

  @FXML
  private void visitWithdrawCash() {
    SceneController.setScene("WithdrawCash.fxml", "Withdraw Cash",null);
  }

  @FXML
  private void visitPayBill() {
    SceneController.setScene("PayBill.fxml", "Pay Bill",null);
  }

  @FXML
  private void visitBookTickets() {
    SceneController.setScene("BookTickets.fxml", "Book Tickets",null);
  }

  @FXML
  private void visitCalculators() {
    SceneController.setScene("Calculator.fxml", "Calculators",null);
  }
}
