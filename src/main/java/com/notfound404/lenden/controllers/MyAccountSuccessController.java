package com.notfound404.lenden.controllers;

import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MyAccountSuccessController {

    @FXML
    private TextField userNameTextField, phoneNumberTextField, nidOrPassportTextField;

    @FXML
    private PasswordField passwordField;

    UserService userService = new UserService();

    @FXML
    private void initialize() {
        userNameTextField.setText(userService.getCurrentUser().getName());
        userNameTextField.setEditable(false);

        phoneNumberTextField.setText(userService.getCurrentUser().getPhone());
        phoneNumberTextField.setEditable(false);

        nidOrPassportTextField.setText(userService.getCurrentUser().getNid());
        nidOrPassportTextField.setEditable(false);

        passwordField.setText(String.valueOf(userService.getCurrentUser().getPin()));
        passwordField.setEditable(false);
    }

}
