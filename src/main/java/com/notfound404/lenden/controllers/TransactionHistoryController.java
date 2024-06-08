package com.notfound404.lenden.controllers;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import com.notfound404.lenden.models.Transaction;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TransactionHistoryController {

  @FXML
  private FlowPane transactionsFlowPane;

  private TransactionService transactionService;
  private UserService userService;
  private User currentUser;
  private List<Transaction> transactions;

  @FXML
  private void initialize() {
    transactionService = new TransactionService();
    userService = new UserService();
    currentUser = userService.getCurrentUser();

    loadTransactions();
  }

  private void loadTransactions() {
    transactionsFlowPane.getChildren().clear();
    transactions = transactionService.getTransactionsByUser(currentUser);

    if (transactions.isEmpty()) {
      handleNoTransactions();
      return;
    }

    Collections.sort(transactions);

    for (Transaction transaction : transactions) {
      VBox transactionBox = createTransactionBox(transaction);
      transactionsFlowPane.getChildren().add(transactionBox);
    }
  }

  private VBox createTransactionBox(Transaction transaction) {
    VBox transactionBox = new VBox(2);
    transactionBox.setPrefWidth(545);
    transactionBox.getStyleClass().add("transaction-box");

    HBox topBox = new HBox();
    topBox.setPrefWidth(523);

    Label typeLabel = new Label(transaction.getType().toString());
    typeLabel.setPrefWidth(175);
    typeLabel.setPrefHeight(25);
    typeLabel.getStyleClass().add("type");

    HBox trxIdBox = new HBox(2);
    trxIdBox.setAlignment(javafx.geometry.Pos.CENTER);
    trxIdBox.setPrefWidth(174);

    Label trxIdLabel = new Label("Trx ID: " + transaction.getId());
    trxIdLabel.setPrefWidth(130);
    trxIdLabel.setPrefHeight(25);
    trxIdLabel.getStyleClass().add("detail");

    Button copyButton = new Button();
    copyButton.getStyleClass().add("copy-btn");
    FontAwesomeIcon copyIcon = new FontAwesomeIcon();
    copyIcon.glyphNameProperty().set("COPY");
    copyButton.setGraphic(copyIcon);

    copyButton.setOnAction(e -> {
      Clipboard clipboard = Clipboard.getSystemClipboard();
      ClipboardContent content = new ClipboardContent();
      content.putString(transaction.getId());
      clipboard.setContent(content);
    });

    trxIdBox.getChildren().addAll(trxIdLabel, copyButton);

    boolean isDeposit = transaction.getType().equals(TransactionType.ADD_MONEY_BANK)
        || transaction.getType().equals(TransactionType.ADD_MONEY_CARD);

    String amount = (isDeposit ? "+ " : "- ") + (transaction.getAmount() - transaction.getCharge()) + " BDT";
    Label amountLabel = new Label(amount);
    amountLabel.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
    amountLabel.setPrefWidth(174);
    amountLabel.setPrefHeight(25);
    amountLabel.getStyleClass().add(isDeposit ? "positive-amount" : "negative-amount");

    topBox.getChildren().addAll(typeLabel, trxIdBox, amountLabel);

    HBox middleBox = new HBox();
    middleBox.setPrefWidth(523);

    Label destinationLabel = new Label(
        transaction.getDestination().getPrefix() + ": " + transaction.getDestination().getInfo());
    destinationLabel.setPrefWidth(370);
    destinationLabel.getStyleClass().add("detail");

    Label chargeLabel = new Label("Charge: ৳ " + transaction.getCharge());
    chargeLabel.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
    chargeLabel.setPrefWidth(153);
    chargeLabel.getStyleClass().add("detail");

    middleBox.getChildren().addAll(destinationLabel, chargeLabel);

    HBox bottomBox = new HBox();
    bottomBox.setPrefWidth(523);

    Label referenceLabel = new Label();
    if (transaction.getReference() != null)
      referenceLabel.setText(transaction.getReference().getPrefix() + ": " + transaction.getReference().getInfo());
    else
      referenceLabel.setText("");
    referenceLabel.setPrefWidth(370);
    referenceLabel.getStyleClass().add("detail");

    // stores date in this format: 12:00am 12/12/12
    String date = new SimpleDateFormat("hh:mm a dd/MM/yy").format(transaction.getDate());
    Label dateLabel = new Label(date);
    dateLabel.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
    dateLabel.setPrefWidth(153);
    dateLabel.getStyleClass().add("detail");

    bottomBox.getChildren().addAll(referenceLabel, dateLabel);

    transactionBox.getChildren().addAll(topBox, middleBox, bottomBox);
    return transactionBox;
  }

  private void handleNoTransactions() {
    Label noTransactionsLabel = new Label("You don't have any transactions yet.");
    noTransactionsLabel.setPrefWidth(545);
    noTransactionsLabel.setAlignment(javafx.geometry.Pos.CENTER);
    noTransactionsLabel.getStyleClass().add("no-transactions");
    transactionsFlowPane.getChildren().add(noTransactionsLabel);
    return;
  }

}
