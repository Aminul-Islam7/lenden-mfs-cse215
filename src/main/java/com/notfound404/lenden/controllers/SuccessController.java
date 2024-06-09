package com.notfound404.lenden.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import com.notfound404.lenden.models.Transaction;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class SuccessController {

  @FXML
  private Text dateText, transactionIdText, destinationText, amountText, balanceText, referenceText;

  @FXML
  private Label successLabel; 

  private String billType;
  
  public void setBillType(String billType) {
    this.billType = billType;
  }

  @FXML
  private void initialize() {
    List<Transaction> transactions = new TransactionService().loadTransactions();
    setTransactionDetails(transactions.get(transactions.size() - 1));
  }

  public void setTransactionDetails(Transaction transaction) {

    String date = new SimpleDateFormat("hh:mm a\ndd/MM/yyyy").format(transaction.getDate());

    dateText.setText(date);
    transactionIdText.setText(transaction.getId());
    destinationText.setText(transaction.getDestination().getInfo());

    double charge = transaction.getCharge();
    double totalAmount = transaction.getAmount();
    String amount = "৳ " + String.format("%.2f", totalAmount) + "\n";
    amount += transaction.getCharge() > 0
        ? "৳ " + String.format("%.2f", totalAmount - charge)
            + " + ৳ " + String.format("%.2f", charge)
        : "+ No charge";

    amountText.setText(amount);

    String balance = "৳ " + String.format("%.2f", new UserService().getCurrentUser().getBalance());

    balanceText.setText(balance);
    if (referenceText != null)
      referenceText.setText(transaction.getReference().getInfo());

  }

}
