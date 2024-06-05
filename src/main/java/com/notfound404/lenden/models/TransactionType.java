package com.notfound404.lenden.models;

public enum TransactionType {
  ADD("Add Money"),
  PAYMENT("Make Payment"),
  SEND("Send Money"),
  TOPUP("Mobile Top-up"),
  TOBANK("Lenden to Bank"),
  WITHDRAW("Withdraw Cash"),
  BILL("Pay Bill");

  private final String type;

  TransactionType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return type;
  }
}
