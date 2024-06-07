package com.notfound404.lenden.controllers;

public class HomeController {

  public void visitAddMoney() {
    SceneController.setScene("AddMoney.fxml", "Add Money");
  }

  public void visitMakePayment() {
    SceneController.setScene("MakePayment.fxml", "Make Payment");
  }

  public void visitSendMoney() {
    SceneController.setScene("SendMoney.fxml", "Send Money");
  }

  public void visitMobileTopUp() {
    SceneController.setScene("MobileTopUp.fxml", "Mobile Top Up");
  }

  public void visitLendenToBank() {
    SceneController.setScene("LendenToBank.fxml", "Lenden To Bank");
  }

  public void visitWithdrawCash() {
    SceneController.setScene("WithdrawCash.fxml", "Withdraw Cash");
  }

  public void visitPayBill() {
    SceneController.setScene("PayBill.fxml", "Pay Bill");
  }

  public void visitBookTickets() {
    SceneController.setScene("BookTickets.fxml", "Book Tickets");
  }

  public void visitCalculators() {
    SceneController.setScene("Calculator.fxml", "Calculators");
  }

  public void visitTransactionHistory() {
    SceneController.setScene("TransactionHistory.fxml", "Transaction History");
  }

}
