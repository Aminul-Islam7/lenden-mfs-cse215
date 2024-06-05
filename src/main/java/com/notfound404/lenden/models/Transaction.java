package com.notfound404.lenden.models;

import java.util.Date;

public class Transaction {

  private String id;
  private User user;
  private TransactionType type;
  private String destination;
  private double amount;
  private double charge;
  private Date date;
  private String reference;

  public Transaction(String id, User user, TransactionType type, String destination, double amount, double charge,
      Date date, String reference) {
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

  public String getDestination() {
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

  public String getReference() {
    return reference;
  }

  @Override
  public String toString() {
    return "Transaction [amount=" + amount + ", charge=" + charge + ", date=" + date + ", destination=" + destination
        + ", id=" + id + ", reference=" + reference + ", type=" + type + ", user=" + user + "]";
  }

}
