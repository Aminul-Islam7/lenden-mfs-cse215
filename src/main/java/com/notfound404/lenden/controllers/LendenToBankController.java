package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.TransactionInfo;
import com.notfound404.lenden.models.TransactionLimit;
import com.notfound404.lenden.models.TransactionType;
import com.notfound404.lenden.services.TransactionService;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LendenToBankController {

    @FXML
    private ChoiceBox<String> selectBankChoiceBox;

    @FXML
    private TextField accountHolderNameTextField, accountNumberTextField, transferAmountTextField;

    @FXML
    private PasswordField pinPasswordField;

    @FXML
    private Label errorLabel;

    private String[] banks = {
            "AB Bank Limited",
            "Bangladesh Development Bank",
            "Dhaka Bank Limited",
            "Community Bank Bangladesh",
            "IFIC Bank PLC",
            "Sonali Bank",
            "Social Islami Bank PLC",
            "First Security Islami Bank PLC.",
            "Rajshahi Krishi Unnayan Bank",
            "Mutual Trust Bank Ltd.",
            "NRBC Bank PLC.",
            "Southeast Bank Limited",
            "Pubali Bank PLC",
            "The Premier Bank PLC",
            "Midland Bank Limited",
    };

    @FXML
    private void initialize() {
        selectBankChoiceBox.setValue("Select a Bank");
        selectBankChoiceBox.getItems().addAll(banks);
    }

    public void handleBankTransfer() {
        UserService userService = new UserService();
        TransactionService transactionService = new TransactionService();

        if (selectBankChoiceBox.getValue().equals("Select a Bank")) {
            errorLabel.setText("Please select a Bank");
            return;
        }

        if (accountHolderNameTextField.getText().isEmpty() || accountNumberTextField.getText().isEmpty()
                || transferAmountTextField.getText().isEmpty() || pinPasswordField.getText().isEmpty()) {
            errorLabel.setText("Please fill all the fields");
            return;
        }

        if (!transferAmountTextField.getText().matches("[0-9]+(\\.\\d+)?")
                || Double.parseDouble(transferAmountTextField.getText()) <= 0.0) {
            errorLabel.setText("Invalid Amount");
            return;
        }

        double amountSpent = transactionService.getSpentAmount(userService.getCurrentUser(), "Lenden to Bank");
        if (amountSpent + Double.parseDouble(transferAmountTextField.getText()) > TransactionLimit.LENDEN_TO_BANK
                .getLimit()) {
            errorLabel.setText("You have reached your daily limit for Lenden to Bank");
            return;
        }

        if (Double.parseDouble(transferAmountTextField.getText()) > userService.getCurrentUser().getBalance()) {
            errorLabel.setText("Insufficient Balance");
            return;
        }

        if (!accountNumberTextField.getText().matches("[0-9]+")) {
            errorLabel.setText("Invalid Account Number");
            return;
        }

        if (!pinPasswordField.getText().matches("[0-9]+")) {
            errorLabel.setText("Invalid PIN");
            return;
        }

        int currentUserPin = userService.getCurrentUser().getPin();
        if (!pinPasswordField.getText().equals(String.valueOf(currentUserPin))) {
            errorLabel.setText("Invalid PIN");
            return;
        }

        TransactionInfo destination = new TransactionInfo("Bank", selectBankChoiceBox.getValue().toString());
        TransactionInfo reference = new TransactionInfo("Account Number", accountNumberTextField.getText());
        double charge = 0.02 * (Double.parseDouble(transferAmountTextField.getText()));
        double amount = Double.parseDouble(transferAmountTextField.getText()) + charge;

        transactionService.addTransaction(userService.getCurrentUser(), TransactionType.LENDEN_TO_BANK,
                destination, amount, charge,
                reference);

        userService.deductBalance(userService.getCurrentUser(), amount);

        SceneController.setScene("LendenToBankSuccess.fxml", "Lenden to Bank");

    }

}
