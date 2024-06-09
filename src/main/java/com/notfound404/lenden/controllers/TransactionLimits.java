package com.notfound404.lenden.controllers;

import java.lang.classfile.Label;

import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;

public class TransactionLimits {

    @FXML
    private Label sendMoneySpentLabel,
            mobileTopUpSpentLabel,
            addMoneySpentLabel,
            lendenToBankSpentLabel,
            withdrawSpentLabel,
            sendMoneyRemainingLabel,
            mobileTopUpRemainingtLabel,
            addMoneyRemainingLabel,
            lendenToBankRemainingLabel,
            withdrawRemainingLabel;

    @FXML
    private void initialize() {
        User currentUser = new UserService().getCurrentUser();
        TransactionService transactionService = new TransactionService();
        double sendMoneySpent = transactionService.getSpentAmount(currentUser, );
    }
}