package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PercentageCalcController {

    @FXML
    private TextField inputTextField1, inputTextField2, resultTextField1, inputTextField3, inputTextField4,
            resultTextField2;

    @FXML
    private Label errorLabel1, errorLabel2;

    @FXML
    void calculate() {
        try {

            if (inputTextField1.getText().isEmpty() && inputTextField2.getText().isEmpty()) {
                errorLabel1.setText("Please fill in at least two fields");
            } else if (inputTextField1.getText().isEmpty() && resultTextField1.getText().isEmpty()) {
                errorLabel1.setText("Please fill in at least two fields");
            } else if (inputTextField2.getText().isEmpty() && resultTextField1.getText().isEmpty()) {
                errorLabel1.setText("Please fill in at least two fields");
            } else if (inputTextField1.getText().isEmpty()) {
                double secondNumberInt = Double.parseDouble(inputTextField2.getText());
                double resultNumberInt = Double.parseDouble(resultTextField1.getText());

                double calculatedNumber = (resultNumberInt / secondNumberInt) * 100;

                String formatCalculatedNum = String.format("%.1f", calculatedNumber);
                inputTextField1.setText(String.valueOf(formatCalculatedNum));
                errorLabel1.setText("");
            } else if (inputTextField2.getText().isEmpty()) {
                double firstNumberInt = Double.parseDouble(inputTextField1.getText());
                double resultNumberInt = Double.parseDouble(resultTextField1.getText());

                double calculatedNumber = (resultNumberInt / firstNumberInt) * 100;

                String formatCalculatedNum = String.format("%.1f", calculatedNumber);
                inputTextField2.setText(String.valueOf(formatCalculatedNum));
                errorLabel1.setText("");
            } else if (resultTextField1.getText().isEmpty()) {
                double firstNumberInt = Double.parseDouble(inputTextField1.getText());
                double secondNumberInt = Double.parseDouble(inputTextField2.getText());

                double calculatedNumber = (firstNumberInt * secondNumberInt) / 100;

                String formatCalculatedNum = String.format("%.1f", calculatedNumber);
                resultTextField1.setText(String.valueOf(formatCalculatedNum));
                errorLabel1.setText("");
            } else {
                errorLabel1.setText("Please keep one field empty");
            }
        } catch (NumberFormatException e) {
            errorLabel1.setText("Invalid Input!");
        } catch (ArithmeticException e) {
            errorLabel1.setText("Invalid Input!");
        }

    }

    @FXML
    void calculateChange() {
        try {
            double firstNumberInt = Double.parseDouble(inputTextField3.getText());
            double secondNumberInt = Double.parseDouble(inputTextField4.getText());

            double calculatedNumber = ((secondNumberInt - firstNumberInt) / firstNumberInt) * 100;

            String formatCalculatedNum = String.format("%.1f", calculatedNumber);
            resultTextField2.setText(String.valueOf(formatCalculatedNum));

            errorLabel2.setText("");

        } catch (NumberFormatException e) {
            errorLabel2.setText("Invalid Input!");
        } catch (ArithmeticException e) {
            errorLabel2.setText("Invalid Input!");
        }
    }
}
