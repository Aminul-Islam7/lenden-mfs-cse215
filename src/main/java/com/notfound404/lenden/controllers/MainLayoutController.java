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
  private Button backButton, forwardButton, balanceButton, homeButton, transactionsButton, accountSummaryButton,
      transactionLimitsButton, withdrawLocationsButton, myAccountButton;

  @FXML
  private Label sceneLabel;

  @FXML
  public void initialize() {
    SceneController.setContentPane(contentPane);
    SceneController.setMainLayoutController(this);
    AuthenticationController.setMainLayoutController(this);

    visitHome();

    updateNavButtonsVisibility();
    updateMenuButtons();
  }

  public void setSceneLabel(String label) {
    sceneLabel.setText(label);
  }

  public Label getSceneLabel() {
    return sceneLabel;
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

  public void updateMenuButtons() {
    removeSelectedStyleClasses();

    String currentView = SceneController.getCurrentView();
    switch (currentView) {
      case "Home.fxml":
        homeButton.getStyleClass().add("selected");
        break;
      case "TransactionHistory.fxml":
        transactionsButton.getStyleClass().add("selected");
        break;
      case "AccountSummary.fxml":
        accountSummaryButton.getStyleClass().add("selected");
        break;
      case "TransactionLimits.fxml":
        transactionLimitsButton.getStyleClass().add("selected");
        break;
      case "WithdrawLocations.fxml":
        withdrawLocationsButton.getStyleClass().add("selected");
        break;
      case "MyAccount.fxml":
        myAccountButton.getStyleClass().add("selected");
        break;
    }
  }

  private void removeSelectedStyleClasses() {
    homeButton.getStyleClass().remove("selected");
    transactionsButton.getStyleClass().remove("selected");
    accountSummaryButton.getStyleClass().remove("selected");
    transactionLimitsButton.getStyleClass().remove("selected");
    withdrawLocationsButton.getStyleClass().remove("selected");
    myAccountButton.getStyleClass().remove("selected");
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
    balanceButton.setText("৳ " + String.format("%.2f", balance));

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

  @FXML
  private void visitHome() {
    String message = new UserService().getCurrentUser() == null ? "Welcome"
        : "Welcome, " + new UserService().getCurrentUser().getName();

    SceneController.setScene("Home.fxml", message);
  }

  @FXML
  private void visitTransactionHistory() {
    SceneController.setScene("TransactionHistory.fxml", "Transaction History");
  }

  @FXML
  private void visitAccountSummary() {
    SceneController.setScene("AccountSummary.fxml", "Account Summary");
  }

  @FXML
  private void visitTransactionLimits() {
    SceneController.setScene("TransactionLimits.fxml", "Transaction Limits");
  }

  @FXML
  private void visitWithdrawLocations() {
    SceneController.setScene("WithdrawLocations.fxml", "Lenden Agent Locations");
  }

  @FXML
  private void visitMyAccount() {
    SceneController.setScene("MyAccount.fxml", "My Account");
  }

  @FXML
  private void exitApp() {
    System.exit(0);
  }

}
