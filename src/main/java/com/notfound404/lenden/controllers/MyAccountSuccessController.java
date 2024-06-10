package com.notfound404.lenden.controllers;

import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MyAccountSuccessController {

    @FXML
    private TextField userNameTextField, phoneNumberTextField, nidOrPassportTextField, ageTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize() {
        UserService userService = new UserService();

        userNameTextField.setText(userService.getCurrentUser().getName());

        phoneNumberTextField.setText(userService.getCurrentUser().getPhone());

        nidOrPassportTextField.setText(userService.getCurrentUser().getNid());

        ageTextField.setText(String.valueOf(userService.getCurrentUser().getAge()));
    }

}
