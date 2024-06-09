package com.notfound404.lenden.models;

public enum TransactionLimit {
  ADD_MONEY(50_000.0),
  SEND_MONEY(25_000.0),
  MOBILE_TOP_UP(10_000.0),
  LENDEN_TO_BANK(50_000.0),
  WITHDRAW_CASH(25_000.0);

  private final double limit;

  TransactionLimit(double limit) {
    this.limit = limit;
  }

  public double getLimit() {
    return limit;
  }

  @Override
  public String toString() {
    return String.valueOf(limit);
  }
}
