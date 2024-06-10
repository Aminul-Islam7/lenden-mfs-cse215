package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class MyAccountController {

    @FXML
    private TextField userNameTextField, phoneNumberTextField, nidOrPassportTextField;

    @FXML
    private PasswordField currentPasswordField, newPasswordField;

    @FXML
    private Label errorLabel;

    UserService userService = new UserService();

    @FXML
    private void initialize() {
        userNameTextField.setText(userService.getCurrentUser().getName());
        phoneNumberTextField.setText(userService.getCurrentUser().getPhone());
        phoneNumberTextField.setEditable(false);
        nidOrPassportTextField.setText(userService.getCurrentUser().getNid());
    }

    public void handleUpdateProfile() {

        if (userNameTextField.getText().isEmpty() || phoneNumberTextField.getText().isEmpty()
                || nidOrPassportTextField.getText().isEmpty() || currentPasswordField.getText().isEmpty()
                || newPasswordField.getText().isEmpty()) {
            errorLabel.setText("Please fill all the fields");
            return;
        }

        if (currentPasswordField.getText().equals(newPasswordField.getText())) {
            errorLabel.setText("New password cannot be the same as the current password");
            return;
        }

        int currentUserPin = userService.getCurrentUser().getPin();
        if (!currentPasswordField.getText().equals(String.valueOf(currentUserPin))) {
            errorLabel.setText("Current password is incorrect");
            return;
        }

        User user = new User(userNameTextField.getText(), phoneNumberTextField.getText(),
                nidOrPassportTextField.getText(),
                userService.getCurrentUser().getAge(), Integer.parseInt(newPasswordField.getText()),
                userService.getCurrentUser().getBalance());

        userService.updateUser(user);

        SceneController.setScene("MyAccountSuccess.fxml", "My Account");

    }

}
