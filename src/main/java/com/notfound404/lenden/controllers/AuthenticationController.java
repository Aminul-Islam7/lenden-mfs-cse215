package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.UserService;

public class AuthenticationController {

    @FXML
    private TextField nameField, phoneField, nidField, ageField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Button registerButton, loginButton;

    @FXML
    private Label errorLabel;

    private UserService userService;

    private static MainLayoutController mainLayoutController;

    @FXML
    public void initialize() {
        userService = new UserService();
    }

    public static void setMainLayoutController(MainLayoutController controller) {
        mainLayoutController = controller;
    }

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String nid = nidField.getText();

        if (name.isEmpty() || phone.isEmpty() || nid.isEmpty() || ageField.getText().isEmpty()
                || pinField.getText().isEmpty()) {
            errorLabel.setText("Please fill all fields");
            return;
        }

        if (!ageField.getText().matches("\\d{1,3}")) {
            errorLabel.setText("Invalid age");
            return;
        }

        if (!pinField.getText().matches("\\d{4}")) {
            errorLabel.setText("PIN must be a 4-digit number");
            return;
        }

        int age = Integer.parseInt(ageField.getText());
        int pin = Integer.parseInt(pinField.getText());

        if (age < 18) {
            errorLabel.setText("You must be at least 18 years old to register");
            return;
        } else if (age > 150) {
            errorLabel.setText("You are too old to register");
            return;
        }

        if (!phone.matches("01[2-9]\\d{8}")) {
            errorLabel.setText("Invalid phone number");
            return;
        }

        boolean success = userService.registerUser(name, phone, nid, age, pin, 20.00);

        if (success) {
            UserService userService = new UserService();
            userService.loginUser(phone, pin);
            SceneController.showMainStage();
            mainLayoutController.initialize();
        } else
            errorLabel.setText("This phone number is already associated with an account");

    }

    @FXML
    private void handleLogin() {
        String phone = phoneField.getText();

        if (phone.isEmpty() || pinField.getText().isEmpty()) {
            errorLabel.setText("Please fill all the fields");
            return;
        }

        if (!phone.matches("01[3-9]\\d{8}")) {
            errorLabel.setText("Invalid phone number");
            return;
        }

        if (!pinField.getText().matches("\\d{4}")) {
            errorLabel.setText("PIN must be a 4-digit number");
            return;
        }

        int pin = Integer.parseInt(pinField.getText());

        User user = userService.loginUser(phone, pin);
        if (user != null) {
            SceneController.showMainStage();
            mainLayoutController.initialize();
        } else
            errorLabel.setText("Incorrect phone number or PIN");

    }

    @FXML
    private void switchToRegisterScene() {
        SceneController.switchToScene("Register.fxml");
    }

    @FXML
    private void switchToLoginScene() {
        SceneController.switchToScene("Login.fxml");
    }

}
