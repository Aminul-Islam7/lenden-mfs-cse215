package com.notfound404.lenden.controllers;

import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.UserService;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    AuthenticationController.setMainLayoutController(this);

    String message = new UserService().getCurrentUser() == null ? "Welcome"
        : "Welcome, " + new UserService().getCurrentUser().getName();

    SceneController.setScene("Home.fxml", message);

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
    if (SceneController.isBookTicketsWebView()) {
      backButton.setVisible(SceneController.bookTicketsWebViewController.webHistory.getCurrentIndex() >= 0);
      forwardButton.setVisible(SceneController.bookTicketsWebViewController.webHistory
          .getCurrentIndex() < SceneController.bookTicketsWebViewController.webEngine.getHistory().getEntries().size()
              - 1);
    } else {
      backButton.setVisible(SceneController.viewList.size() > 1);
      forwardButton.setVisible(SceneController.visitedViewList.size() > 0);
    }
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
