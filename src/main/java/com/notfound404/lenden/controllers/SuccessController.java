package com.notfound404.lenden.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import com.notfound404.lenden.models.Transaction;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SuccessController {

  @FXML
  private Text dateText, transactionIdText, destinationText, amountText, balanceText, referenceText;

  @FXML
  private void initialize() {
    AMFromBankController.setSuccessController(this);
    WithdrawCashController.setSuccessController(this);

    List<Transaction> transactions = new TransactionService().loadTransactions();
    setTransactionDetails(transactions.get(transactions.size() - 1));

  }

  public void setTransactionDetails(Transaction transaction) {

    String date = new SimpleDateFormat("hh:mm a\ndd/MM/yyyy").format(transaction.getDate());

    dateText.setText(date);
    transactionIdText.setText(transaction.getId());
    destinationText.setText(transaction.getDestination().getInfo());

    double totalAmount = transaction.getAmount() + transaction.getCharge();
    String amount = "৳ " + totalAmount + "\n";
    amount += transaction.getCharge() > 0
        ? "৳ " + transaction.getAmount() + "৳ " + transaction.getCharge()
        : "+ No charge";

    amountText.setText(amount);

    balanceText.setText("৳ " + new UserService().getCurrentUser().getBalance());
    if (referenceText != null)
      referenceText.setText(transaction.getReference().getInfo());
  }

}