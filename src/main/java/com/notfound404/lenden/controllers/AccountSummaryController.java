package com.notfound404.lenden.controllers;

import java.util.List;

import com.notfound404.lenden.models.Transaction;
import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AccountSummaryController {

    @FXML
    private Label balanceLabel, expensesLabel, cashInLabel, cashOutLabel, expensesPeriodLabel, cashInPeriodLabel,
            cashOutPeriodLabel;

    @FXML
    private Button button24Hours, button7Days, button30Days, button6Months, button1Year;

    private List<Transaction> transactions;

    private final long ONE_DAY = 24 * 60 * 60 * 1000;
    private final long ONE_WEEK = 7 * ONE_DAY;
    private final long ONE_MONTH = 30 * ONE_DAY;
    private final long ONE_YEAR = 365 * ONE_DAY;

    @FXML
    private void initialize() {
        User currentUser = new UserService().getCurrentUser();
        transactions = new TransactionService().getTransactionsByUser(currentUser);
        balanceLabel.setText(String.format("%.2f", currentUser.getBalance()));
        handle24Hours();
    }

    @FXML
    private void handle24Hours() {
        expensesPeriodLabel.setText("24 hours");
        cashInPeriodLabel.setText("24 hours");
        cashOutPeriodLabel.setText("24 hours");

        button24Hours.getStyleClass().add("selected");
        button7Days.getStyleClass().remove("selected");
        button30Days.getStyleClass().remove("selected");
        button6Months.getStyleClass().remove("selected");
        button1Year.getStyleClass().remove("selected");

        double cashIn = 0.0, cashOut = 0.0, expenses = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getTime() > System.currentTimeMillis() - ONE_DAY) {
                if (transaction.getType().getType().equals("Add Money")) {
                    cashIn += transaction.getAmount();
                } else if (transaction.getType().getType().equals("Pay Bill")) {
                    expenses += transaction.getCharge();
                } else {
                    cashOut += transaction.getAmount();
                }
            }
        }

        cashInLabel.setText(String.format("%.2f", cashIn));
        cashOutLabel.setText(String.format("%.2f", cashOut));
        expensesLabel.setText(String.format("%.2f", expenses));
    }

    @FXML
    private void handle7Days() {
        expensesPeriodLabel.setText("7 days");
        cashInPeriodLabel.setText("7 days");
        cashOutPeriodLabel.setText("7 days");

        button24Hours.getStyleClass().remove("selected");
        button7Days.getStyleClass().add("selected");
        button30Days.getStyleClass().remove("selected");
        button6Months.getStyleClass().remove("selected");
        button1Year.getStyleClass().remove("selected");

        double cashIn = 0.0, cashOut = 0.0, expenses = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getTime() > System.currentTimeMillis() - ONE_WEEK) {
                if (transaction.getType().getType().equals("Add Money")) {
                    cashIn += transaction.getAmount();
                } else if (transaction.getType().getType().equals("Pay Bill")) {
                    expenses += transaction.getCharge();
                } else {
                    cashOut += transaction.getAmount();
                }
            }
        }

        cashInLabel.setText(String.format("%.2f", cashIn));
        cashOutLabel.setText(String.format("%.2f", cashOut));
        expensesLabel.setText(String.format("%.2f", expenses));
    }

    @FXML
    private void handle30Days() {
        expensesPeriodLabel.setText("30 days");
        cashInPeriodLabel.setText("30 days");
        cashOutPeriodLabel.setText("30 days");

        button24Hours.getStyleClass().remove("selected");
        button7Days.getStyleClass().remove("selected");
        button30Days.getStyleClass().add("selected");
        button6Months.getStyleClass().remove("selected");
        button1Year.getStyleClass().remove("selected");

        double cashIn = 0.0, cashOut = 0.0, expenses = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getTime() > System.currentTimeMillis() - ONE_MONTH) {
                if (transaction.getType().getType().equals("Add Money")) {
                    cashIn += transaction.getAmount();
                } else if (transaction.getType().getType().equals("Pay Bill")) {
                    expenses += transaction.getCharge();
                } else {
                    cashOut += transaction.getAmount();
                }
            }
        }

        cashInLabel.setText(String.format("%.2f", cashIn));
        cashOutLabel.setText(String.format("%.2f", cashOut));
        expensesLabel.setText(String.format("%.2f", expenses));
    }

    @FXML
    private void handle6Months() {
        expensesPeriodLabel.setText("6 months");
        cashInPeriodLabel.setText("6 months");
        cashOutPeriodLabel.setText("6 months");

        button24Hours.getStyleClass().remove("selected");
        button7Days.getStyleClass().remove("selected");
        button30Days.getStyleClass().remove("selected");
        button6Months.getStyleClass().add("selected");
        button1Year.getStyleClass().remove("selected");

        double cashIn = 0.0, cashOut = 0.0, expenses = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getTime() > System.currentTimeMillis() - ONE_YEAR) {
                if (transaction.getType().getType().equals("Add Money")) {
                    cashIn += transaction.getAmount();
                } else if (transaction.getType().getType().equals("Pay Bill")) {
                    expenses += transaction.getCharge();
                } else {
                    cashOut += transaction.getAmount();
                }
            }
        }

        cashInLabel.setText(String.format("%.2f", cashIn));
        cashOutLabel.setText(String.format("%.2f", cashOut));
        expensesLabel.setText(String.format("%.2f", expenses));
    }

    @FXML
    private void handle1Year() {
        expensesPeriodLabel.setText("1 year");
        cashInPeriodLabel.setText("1 year");
        cashOutPeriodLabel.setText("1 year");

        button24Hours.getStyleClass().remove("selected");
        button7Days.getStyleClass().remove("selected");
        button30Days.getStyleClass().remove("selected");
        button6Months.getStyleClass().remove("selected");
        button1Year.getStyleClass().add("selected");

        double cashIn = 0.0, cashOut = 0.0, expenses = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getTime() > System.currentTimeMillis() - 365 * 24 * 60 * 60 * 1000) {
                if (transaction.getType().getType().equals("Add Money")) {
                    cashIn += transaction.getAmount();
                } else if (transaction.getType().getType().equals("Pay Bill")) {
                    expenses += transaction.getCharge();
                } else {
                    cashOut += transaction.getAmount();
                }
            }
        }

        cashInLabel.setText(String.format("%.2f", cashIn));
        cashOutLabel.setText(String.format("%.2f", cashOut));
        expensesLabel.setText(String.format("%.2f", expenses));
    }
}
