package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalculatorController {

    public void visitPercentageCalc() {
        SceneController.setScene("PercentageCalc.fxml", "Percentage Calculator");
    }

    public void visitInterestCalc() {
        SceneController.setScene("InterestCalc.fxml", "Interest Calculator");
    }

    @FXML
    private TextField textField;

    @FXML
    private Text savedNumbers;
    private String initialNumber = "";
    private String currentNumber = "";
    private String calculationType;

    @FXML
    void addAction() {
        calculationSetup("+");
    }

    @FXML
    void subtractAction() {
        calculationSetup("-");
    }

    @FXML
    void divideAction() {
        calculationSetup("/");
    }

    @FXML
    void multiplicationAction() {
        calculationSetup("x");
    }

    public void calculationSetup(String calculationType) {
        this.calculationType = calculationType;
        initialNumber = currentNumber;
        currentNumber = "";
        savedNumbers.setText(initialNumber + " " + calculationType);
    }

    @FXML
    void calculate() {
        double firstNumberInt = Double.parseDouble(initialNumber);
        double secondNumberInt = Double.parseDouble(currentNumber);

        switch (calculationType) {
            case "+" -> {
                double calculatedNumber = firstNumberInt + secondNumberInt;
                savedNumbers.setText(initialNumber + " + " + currentNumber + " = " + calculatedNumber);
                textField.setText(String.valueOf(calculatedNumber));
            }
            case "-" -> {
                double calculatedNumber = firstNumberInt - secondNumberInt;
                savedNumbers.setText(initialNumber + " - " + currentNumber + " = " + calculatedNumber);
                textField.setText(String.valueOf(calculatedNumber));
            }
            case "/" -> {
                double calculatedNumber = firstNumberInt / (double) secondNumberInt;
                savedNumbers.setText(initialNumber + " / " + currentNumber + " = " + calculatedNumber);
                textField.setText(String.valueOf(calculatedNumber));
            }
            case "x" -> {
                double calculatedNumber = firstNumberInt * secondNumberInt;
                savedNumbers.setText(initialNumber + " x " + currentNumber + " = " + calculatedNumber);
                textField.setText(String.valueOf(calculatedNumber));
            }
        }
    }

    @FXML
    void clearTextField() {
        currentNumber = "";
        textField.setText("");
        savedNumbers.setText("");
    }

    @FXML
    void deleteDigit() {
        currentNumber = "";
        if (!currentNumber.isEmpty()) {
            textField.setText(currentNumber.substring(0, currentNumber.length() - 1));
        }
        updateTextField();
    }

    @FXML
    void numBtn0Clicked() {
        if (!currentNumber.equals("") || !textField.getText().equals("0")) {
            addNumber("0");
        }
    }

    @FXML
    void numBtn1Clicked() {
        addNumber("1");
    }

    @FXML
    void numBtn2Clicked() {
        addNumber("2");
    }

    @FXML
    void numBtn3Clicked() {
        addNumber("3");
    }

    @FXML
    void numBtn4Clicked() {
        addNumber("4");
    }

    @FXML
    void numBtn5Clicked() {
        addNumber("5");
    }

    @FXML
    void numBtn6Clicked() {
        addNumber("6");
    }

    @FXML
    void numBtn7Clicked() {
        addNumber("7");
    }

    @FXML
    void numBtn8Clicked() {
        addNumber("8");
    }

    @FXML
    void numBtn9Clicked() {
        addNumber("9");
    }

    @FXML
    void dotBtnClicked() {
        addNumber(".");
    }

    public void updateTextField() {
        textField.setText(currentNumber);
    }

    public void addNumber(String number) {
        if (!currentNumber.isEmpty() && currentNumber.equals("0")) {
            currentNumber = number;
        } else {
            currentNumber += number;
        }
        updateTextField();
    }

}
