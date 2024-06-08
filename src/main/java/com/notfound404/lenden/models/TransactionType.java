package com.notfound404.lenden.models;

public enum TransactionType {
  ADD_MONEY_BANK("Add Money"),
  ADD_MONEY_CARD("Add Money"),
  MAKE_PAYMENT("Make Payment"),
  SEND_MONEY("Send Money"),
  MOBILE_TOP_UP("Mobile Top-up"),
  LENDEN_TO_BANK("Lenden to Bank"),
  WITHDRAW_CASH("Withdraw Cash"),
  PAY_BILL_ELECTRICITY("Pay Bill"),
  PAY_BILL_GAS("Pay Bill"),
  PAY_BILL_WATER("Pay Bill"),
  PAY_BILL_INTERNET("Pay Bill");

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
