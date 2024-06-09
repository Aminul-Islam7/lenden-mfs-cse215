package com.notfound404.lenden.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalculatorController {
    
    public void visitPercentageCalc() {
        SceneController.setScene("PercentageCalc.fxml", "Calculator-Percentage");
    }
    
    public void visitInterestCalc() {
        SceneController.setScene("InterestCalc.fxml", "Calculator-Interest");
    }

    @FXML
    private TextField textField;

    @FXML
    private Text savedNumbers;
    private String initialNumber = "";
    private String currentNumber = "";
    private String calculationType;

    @FXML
    void addAction(ActionEvent event) {
        calculationSetup("+");
    }

    @FXML
    void subtractAction(ActionEvent event) {
        calculationSetup("-");
    }

    @FXML
    void divideAction(ActionEvent event) {
        calculationSetup("/");
    }

    @FXML
    void multiplicationAction(ActionEvent event) {
        calculationSetup("x");
    }

    public void calculationSetup(String calculationType){
        this.calculationType = calculationType;
        initialNumber = currentNumber;
        currentNumber = "";
        savedNumbers.setText(initialNumber + " " + calculationType);
    }

    @FXML
    void calculate(ActionEvent event) {
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
                double calculatedNumber = firstNumberInt / (double)secondNumberInt;
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
    void clearTextField(ActionEvent event) {
        currentNumber = "";
        textField.setText("");
        savedNumbers.setText("");
    }

    @FXML
    void deleteDigit(ActionEvent event) {       
        currentNumber = "";
        if(!currentNumber.isEmpty()){
            textField.setText(currentNumber.substring(0,currentNumber.length()-1));
        }
        updateTextField();
    }

    @FXML
    void numBtn0Clicked(ActionEvent event) {
        if(!currentNumber.equals("") || !textField.getText().equals("0")){
            addNumber("0");
        }
    }

    @FXML
    void numBtn1Clicked(ActionEvent event) {
        addNumber("1");
    }

    @FXML
    void numBtn2Clicked(ActionEvent event) {
        addNumber("2");
    }

    @FXML
    void numBtn3Clicked(ActionEvent event) {
        addNumber("3");
    }

    @FXML
    void numBtn4Clicked(ActionEvent event) {
        addNumber("4");
    }

    @FXML
    void numBtn5Clicked(ActionEvent event) {
        addNumber("5");
    }

    @FXML
    void numBtn6Clicked(ActionEvent event) {
        addNumber("6");
    }

    @FXML
    void numBtn7Clicked(ActionEvent event) {
        addNumber("7");
    }

    @FXML
    void numBtn8Clicked(ActionEvent event) {
        addNumber("8");
    }

    @FXML
    void numBtn9Clicked(ActionEvent event) {
        addNumber("9");
    }

    @FXML
    void dotBtnClicked(ActionEvent event) {
        addNumber(".");
    }

    public void updateTextField(){
        textField.setText(currentNumber);
    }

    public void addNumber(String number){
        if(!currentNumber.isEmpty() && currentNumber.equals("0")){
            currentNumber = number;
        }
        else{
            currentNumber += number;
        }
        updateTextField();
    }

}
