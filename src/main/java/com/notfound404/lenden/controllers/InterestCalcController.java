package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InterestCalcController {
    @FXML
    private TextField inputTextField1;
    @FXML
    private TextField inputTextField2;
    @FXML
    private TextField inputTextField3;
    @FXML
    private TextField inputTextField4;
    @FXML
    private TextField resultTextField1;
    @FXML
    private TextField resultTextField2;

    @FXML
    private String principalNum = "";
    private String interestRate = "";
    private String time = "";
    private String compoundFreq = "";

    @FXML
    private void calculateInterest() {
        calculateSimple();
        calculateCompound();
    }

    private void calculateSimple() {
        try {
            principalNum = inputTextField1.getText();
            interestRate = inputTextField2.getText();
            time = inputTextField3.getText();

            double principle = Double.parseDouble(principalNum);
            double rate = Double.parseDouble(interestRate) / 100;
            double t = Double.parseDouble(time);

            double simpleInterest = principle * rate * t;

            String formatCalculatedNum = String.format("%.2f", simpleInterest);
            resultTextField1.setText(String.valueOf(formatCalculatedNum));

        } catch (NumberFormatException e) {
            resultTextField1.setText("Invalid!");
        }
    }

    private void calculateCompound() {
        try {
            principalNum = inputTextField1.getText();
            interestRate = inputTextField2.getText();
            time = inputTextField3.getText();
            compoundFreq = inputTextField4.getText();

            double principal = Double.parseDouble(principalNum);
            double rate = Double.parseDouble(interestRate) / 100;
            double t = Double.parseDouble(time);
            double freq = Double.parseDouble(compoundFreq);

            double compoundInterest = principal * Math.pow(1 + (rate / freq), (freq * t));

            String formatCalculatedNum = String.format("%.2f", compoundInterest);
            resultTextField2.setText(String.valueOf(formatCalculatedNum));

        } catch (NumberFormatException e) {
            resultTextField2.setText("Invalid!");
        }
    }

}
