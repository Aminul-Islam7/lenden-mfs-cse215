package com.notfound404.lenden.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainLayoutController {

  @FXML
  private AnchorPane contentPane;

  @FXML
  private Button backButton, forwardButton;

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
}
