package com.notfound404.lenden.controllers;

public abstract class AddMoney implements Receivable {

  @Override
  public void processIncomingTransaction() {
    handleAddMoney();
  }

  public abstract void handleAddMoney();
}
