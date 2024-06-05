package com.notfound404.lenden.services;

import com.notfound404.lenden.models.Transaction;
import com.notfound404.lenden.models.User;

import java.io.*;
import java.util.ArrayList;

public class TransactionService {
  private ArrayList<Transaction> transactions;
  private static final String TRANSACTION_FILE_PATH = "src/main/resources/data/transactions.dat";

  public TransactionService() {
    transactions = loadTransactions();
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
    saveTransactions();
  }

  public ArrayList<Transaction> getTransactionsByUser(User user) {
    ArrayList<Transaction> userTransactions = new ArrayList<>();
    for (Transaction transaction : transactions) {
      if (transaction.getUser().equals(user)) {
        userTransactions.add(transaction);
      }
    }
    return userTransactions;
  }

  private ArrayList<Transaction> loadTransactions() {
    ArrayList<Transaction> transactions = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTION_FILE_PATH))) {
      transactions = (ArrayList<Transaction>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return transactions;
  }

  private void saveTransactions() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTION_FILE_PATH))) {
      oos.writeObject(transactions);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
