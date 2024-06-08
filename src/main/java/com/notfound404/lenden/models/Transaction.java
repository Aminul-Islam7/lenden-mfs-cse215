package com.notfound404.lenden.models;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable, Comparable<Transaction> {

  private String id;
  private User user;
  private TransactionType type;
  private TransactionInfo destination;
  private double amount;
  private double charge;
  private Date date;
  private TransactionInfo reference;

  public Transaction(String id, User user, TransactionType type,
      TransactionInfo destination, double amount, double charge,
      Date date, TransactionInfo reference) {
    this.id = id;
    this.user = user;
    this.type = type;
    this.destination = destination;
    this.amount = amount;
    this.charge = charge;
    this.date = date;
    this.reference = reference;
  }

  public String getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public TransactionType getType() {
    return type;
  }

  public TransactionInfo getDestination() {
    return destination;
  }

  public double getAmount() {
    return amount;
  }

  public double getCharge() {
    return charge;
  }

  public Date getDate() {
    return date;
  }

  public TransactionInfo getReference() {
    return reference;
  }

  @Override
  public int compareTo(Transaction other) {
    return other.getDate().compareTo(this.date);
  }

  @Override
  public String toString() {
    return "Transaction [amount=" + amount + ", charge=" + charge + ", date=" + date + ", destination=" + destination
        + ", id=" + id + ", reference=" + reference + ", type=" + type + ", user=" + user + "]";
  }

}
