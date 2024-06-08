package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class PercentageCalcController {

    @FXML
    private TextField inputTextField1;
    @FXML
    private TextField inputTextField2;
    @FXML
    private TextField resultTextField1;
    @FXML
    private TextField inputTextField3;
    @FXML
    private TextField inputTextField4;
    @FXML
    private TextField resultTextField2;

    @FXML
    private String firstNumber = "";
    private String secondNumber = "";

    @FXML
    void calculate(ActionEvent event){
        try{
            firstNumber = inputTextField1.getText();
            secondNumber = inputTextField2.getText();

            double firstNumberInt = Double.parseDouble(firstNumber);
            double secondNumberInt = Double.parseDouble(secondNumber);

            double calculatedNumber = (firstNumberInt/100) * secondNumberInt;
            
            String formatCalculatedNum = String.format("%.2f",calculatedNumber);
            resultTextField1.setText(String.valueOf(formatCalculatedNum));

        }catch(NumberFormatException e){
            resultTextField1.setText("Invalid!");
        }
    }

    @FXML
    void calculateChange(ActionEvent event){
        try{
            firstNumber = inputTextField3.getText();
            secondNumber = inputTextField4.getText();

            double firstNumberInt = Double.parseDouble(firstNumber);
            double secondNumberInt = Double.parseDouble(secondNumber);

            double calculatedNumber = ((secondNumberInt - firstNumberInt) / firstNumberInt) * 100;
            
            String formatCalculatedNum = String.format("%.1f",calculatedNumber);
            resultTextField2.setText(String.valueOf(formatCalculatedNum));
        
        }catch(NumberFormatException e){
            resultTextField2.setText("Invalid!");
        }catch(ArithmeticException e){
            resultTextField2.setText("Invalid!");
        }
    }
}
