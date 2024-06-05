package com.notfound404.lenden.controllers;

import com.notfound404.lenden.services.UserService;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainLayoutController {

  @FXML
  private AnchorPane contentPane;

  @FXML
  private Button backButton, forwardButton, balanceButton;

  @FXML
  private Label sceneLabel;

  @FXML
  public void initialize() {
    SceneController.setContentPane(contentPane);
    SceneController.setMainLayoutController(this);
    SceneController.setScene("Home.fxml", "Welcome");
    updateNavButtonsVisibility();
  }

  public void setSceneLabel(String label) {
    sceneLabel.setText(label);
  }

  public void goBack(ActionEvent e) {
    SceneController.back();
  }

  public void goForward(ActionEvent e) {
    SceneController.forward();
  }

  public void updateNavButtonsVisibility() {
    backButton.setVisible(SceneController.viewList.size() > 1);
    forwardButton.setVisible(SceneController.visitedViewList.size() > 0);
  }

  @FXML
  private void logoutUser() {
    UserService userService = new UserService();
    userService.logoutUser();
    SceneController.showAuthenticationStage();
  }

  @FXML
  private void viewBalance() {
    UserService userService = new UserService();
    double balance = userService.getCurrentUser().getBalance();
    balanceButton.setText("৳ " + balance);

    Task<Void> sleeper = new Task<Void>() {
      @Override
      public Void call() throws Exception {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return null;
      }
    };
    sleeper.setOnSucceeded(e -> balanceButton.setText("View Balance"));

    new Thread(sleeper).start();
  }

}
