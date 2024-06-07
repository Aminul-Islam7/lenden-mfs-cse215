package com.notfound404.lenden.models;

public enum TransactionType {
  ADD_MONEY("Add Money"),
  MAKE_PAYMENT("Make Payment"),
  SEND_MONEY("Send Money"),
  MOBILE_TOPUP("Mobile Top-up"),
  LENDEN_TO_BANK("Lenden to Bank"),
  WITHDRAW_CASH("Withdraw Cash"),
  PAY_BILL("Pay Bill");

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
