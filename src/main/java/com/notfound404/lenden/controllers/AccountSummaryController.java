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
        updatePeriodLabels("24 hours");

        removeSelectedStyleClasses();
        button24Hours.getStyleClass().add("selected");

        calculatePeriodValues(ONE_DAY);
    }

    @FXML
    private void handle7Days() {
        updatePeriodLabels("7 days");

        removeSelectedStyleClasses();
        button7Days.getStyleClass().add("selected");

        calculatePeriodValues(ONE_WEEK);
    }

    @FXML
    private void handle30Days() {
        updatePeriodLabels("30 days");

        removeSelectedStyleClasses();
        button30Days.getStyleClass().add("selected");

        calculatePeriodValues(ONE_MONTH);
    }

    @FXML
    private void handle6Months() {
        updatePeriodLabels("6 months");

        removeSelectedStyleClasses();
        button6Months.getStyleClass().add("selected");

        calculatePeriodValues(6 * ONE_MONTH);
    }

    @FXML
    private void handle1Year() {
        updatePeriodLabels("1 year");

        removeSelectedStyleClasses();
        button1Year.getStyleClass().add("selected");

        calculatePeriodValues(ONE_YEAR);
    }

    private void calculatePeriodValues(long period) {
        double cashIn = 0.0, cashOut = 0.0, expenses = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getTime() > System.currentTimeMillis() - period) {
                if (transaction.getType().getType().equals("Add Money")) {
                    cashIn += transaction.getAmount();
                } else if (transaction.getType().getType().equals("Pay Bill")) {
                    expenses += transaction.getAmount();
                } else {
                    cashOut += transaction.getAmount();
                }
            }
        }

        cashInLabel.setText(String.format("%.2f", cashIn));
        cashOutLabel.setText(String.format("%.2f", cashOut));
        expensesLabel.setText(String.format("%.2f", expenses));
    }

    private void updatePeriodLabels(String period) {
        expensesPeriodLabel.setText(period);
        cashInPeriodLabel.setText(period);
        cashOutPeriodLabel.setText(period);
    }

    private void removeSelectedStyleClasses() {
        button24Hours.getStyleClass().remove("selected");
        button7Days.getStyleClass().remove("selected");
        button30Days.getStyleClass().remove("selected");
        button6Months.getStyleClass().remove("selected");
        button1Year.getStyleClass().remove("selected");
    }
}
