package com.notfound404.lenden.models;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String phone;
    private String nid;
    private int age;
    private int pin;
    private double balance;

    public User(String name, String phone, String nid, int age, int pin, double balance) {
        this.name = name;
        this.phone = phone;
        this.nid = nid;
        this.age = age;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getNid() {
        return nid;
    }

    public int getAge() {
        return age;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }

}
