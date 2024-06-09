package com.notfound404.lenden.services;

import com.notfound404.lenden.models.Transaction;
import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TransactionService {
  private ArrayList<Transaction> transactions;
  private final String TRANSACTION_FILE_PATH = "src/main/resources/data/transactions.dat";
  private final long ONE_DAY = 24 * 60 * 60 * 1000;

  public TransactionService() {
    createFiles();
    transactions = loadTransactions();
  }

  public void addTransaction(User user, TransactionType type,
      TransactionInfo destination, double amount, double charge,
      TransactionInfo reference) {
    Transaction transaction = new Transaction(generateTransactionID(), user, type, destination, amount,
        charge, new Date(), reference);
    transactions.add(transaction);
    saveTransactions();
  }

  private String generateTransactionID() {
    final char[] CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    Random random = new Random(System.currentTimeMillis());
    StringBuilder id = new StringBuilder(10);

    id.append(CHAR_POOL[random.nextInt(26)]);

    for (int i = 1; i < 10; i++) {
      id.append(CHAR_POOL[random.nextInt(CHAR_POOL.length)]);
    }
    return id.toString();
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

  public ArrayList<Transaction> loadTransactions() {
    ArrayList<Transaction> transactions = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTION_FILE_PATH))) {
      transactions = (ArrayList<Transaction>) ois.readObject();
    } catch (EOFException e) {
      return transactions;
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

  private void createFiles() {
    File transactionFile = new File(TRANSACTION_FILE_PATH);
    try {
      transactionFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public double getSpentAmount(User user, String transactionType) {
    double spent = 0.0;
    for (Transaction transaction : transactions) {
      if (transaction.getUser().equals(user)
          && transaction.getType().getType().equals(transactionType)
          && transaction.getDate().getTime() > System.currentTimeMillis() - ONE_DAY) {
        spent += transaction.getAmount();
      }
    }
    return spent;
  }

}
