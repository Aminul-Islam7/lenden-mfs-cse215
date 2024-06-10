package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class MyAccountController {

    @FXML
    private TextField userNameTextField, phoneNumberTextField, nidOrPassportTextField, ageTextField;

    @FXML
    private PasswordField currentPinPasswordField, newPinPasswordField;

    @FXML
    private Label errorLabel;

    UserService userService;
    User currentUser;

    @FXML
    private void initialize() {
        userService = new UserService();
        currentUser = userService.getCurrentUser();

        userNameTextField.setText(currentUser.getName());
        userNameTextField.setPromptText(currentUser.getName());

        phoneNumberTextField.setText(currentUser.getPhone());
        phoneNumberTextField.setPromptText(currentUser.getPhone());

        nidOrPassportTextField.setText(currentUser.getNid());
        nidOrPassportTextField.setPromptText(currentUser.getNid());

        ageTextField.setText(String.valueOf(currentUser.getAge()));
        ageTextField.setPromptText(String.valueOf(currentUser.getAge()));
    }

    public void handleUpdateProfile() {

        if (userNameTextField.getText().isEmpty()
                || phoneNumberTextField.getText().isEmpty()
                || nidOrPassportTextField.getText().isEmpty()
                || ageTextField.getText().isEmpty()
                || currentPinPasswordField.getText().isEmpty()) {
            errorLabel.setText("Please fill all the required (*) fields");
            return;
        }

        if (!ageTextField.getText().matches("\\d{1,3}")) {
            errorLabel.setText("Invalid age");
            return;
        }

        if (Integer.parseInt(ageTextField.getText()) < 18) {
            errorLabel.setText("You must be at least 18 years old to use this service");
            return;
        }

        if (Integer.parseInt(ageTextField.getText()) > 150) {
            errorLabel.setText("You must be under 150 years old to use this service");
            return;
        }

        if (!currentPinPasswordField.getText().equals(String.valueOf(currentUser.getPin()))) {
            errorLabel.setText("Current PIN is incorrect");
            return;
        }

        if (!newPinPasswordField.getText().isEmpty()) {
            if (!newPinPasswordField.getText().matches("\\d{4}"))
                errorLabel.setText("New PIN must be a 4-digit number");
            if (!newPinPasswordField.getText().equals(String.valueOf(currentUser.getPin())))
                errorLabel.setText("New PIN cannot be the same as the current PIN");
            return;
        }

        int newPin = newPinPasswordField.getText().isEmpty() ? currentUser.getPin()
                : Integer.parseInt(newPinPasswordField.getText());

        User user = new User(userNameTextField.getText(), phoneNumberTextField.getText(),
                nidOrPassportTextField.getText(),
                userService.getCurrentUser().getAge(), newPin,
                userService.getCurrentUser().getBalance());

        userService.updateUser(user);

        SceneController.setScene("MyAccountSuccess.fxml", "My Account");
    }

}
