package com.notfound404.lenden.models;

import java.io.Serializable;

public class TransactionInfo implements Serializable {
  private String prefix;
  private String info;

  public TransactionInfo(String prefix, String info) {
    this.prefix = prefix;
    this.info = info;
  }

  public String getPrefix() {
    return prefix;
  }

  public String getInfo() {
    return info;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Override
  public String toString() {
    return prefix + " " + info;
  }
}
