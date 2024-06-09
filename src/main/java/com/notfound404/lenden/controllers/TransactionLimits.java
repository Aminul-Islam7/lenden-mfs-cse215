package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionLimit;
import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class TransactionLimits {

    @FXML
    private Label addMoneySpentLabel,
            sendMoneySpentLabel,
            mobileTopUpSpentLabel,
            lendenToBankSpentLabel,
            withdrawCashSpentLabel,
            addMoneyRemainingLabel,
            sendMoneyRemainingLabel,
            mobileTopUpRemainingLabel,
            lendenToBankRemainingLabel,
            withdrawCashRemainingLabel;

    @FXML
    private ProgressBar addMoneyProgressBar,
            sendMoneyProgressBar,
            mobileTopUpProgressBar,
            lendenToBankProgressBar,
            withdrawCashProgressBar;

    @FXML
    private void initialize() {
        User currentUser = new UserService().getCurrentUser();
        TransactionService transactionService = new TransactionService();
        double addMoneySpent = transactionService.getSpentAmount(currentUser, "Add Money");
        double sendMoneySpent = transactionService.getSpentAmount(currentUser, "Send Money");
        double mobileTopUpSpent = transactionService.getSpentAmount(currentUser, "Mobile Top-Up");
        double lendenToBankSpent = transactionService.getSpentAmount(currentUser, "Lenden to Bank");
        double withdrawCashSpent = transactionService.getSpentAmount(currentUser, "Withdraw Cash");

        double addMoneyRemaining = TransactionLimit.ADD_MONEY.getLimit() - addMoneySpent;
        double sendMoneyRemaining = TransactionLimit.SEND_MONEY.getLimit() - sendMoneySpent;
        double mobileTopUpRemaining = TransactionLimit.MOBILE_TOP_UP.getLimit() - mobileTopUpSpent;
        double lendenToBankRemaining = TransactionLimit.LENDEN_TO_BANK.getLimit() - lendenToBankSpent;
        double withdrawCashRemaining = TransactionLimit.WITHDRAW_CASH.getLimit() - withdrawCashSpent;

        addMoneySpentLabel.setText(String.format("%.2f", addMoneySpent));
        sendMoneySpentLabel.setText(String.format("%.2f", sendMoneySpent));
        mobileTopUpSpentLabel.setText(String.format("%.2f", mobileTopUpSpent));
        lendenToBankSpentLabel.setText(String.format("%.2f", lendenToBankSpent));
        withdrawCashSpentLabel.setText(String.format("%.2f", withdrawCashSpent));

        addMoneyRemainingLabel.setText(String.format("%.2f", addMoneyRemaining));
        sendMoneyRemainingLabel.setText(String.format("%.2f", sendMoneyRemaining));
        mobileTopUpRemainingLabel.setText(String.format("%.2f", mobileTopUpRemaining));
        lendenToBankRemainingLabel.setText(String.format("%.2f", lendenToBankRemaining));
        withdrawCashRemainingLabel.setText(String.format("%.2f", withdrawCashRemaining));

        addMoneyProgressBar.setProgress(addMoneySpent / TransactionLimit.ADD_MONEY.getLimit());
        sendMoneyProgressBar.setProgress(sendMoneySpent / TransactionLimit.SEND_MONEY.getLimit());
        mobileTopUpProgressBar.setProgress(mobileTopUpSpent / TransactionLimit.MOBILE_TOP_UP.getLimit());
        lendenToBankProgressBar.setProgress(lendenToBankSpent / TransactionLimit.LENDEN_TO_BANK.getLimit());
        withdrawCashProgressBar.setProgress(withdrawCashSpent / TransactionLimit.WITHDRAW_CASH.getLimit());
    }
}