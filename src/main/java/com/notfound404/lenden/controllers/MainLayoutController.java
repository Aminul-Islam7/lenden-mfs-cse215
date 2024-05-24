package com.notfound404.lenden.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainLayoutController {

  @FXML
  private AnchorPane contentPane;

  @FXML
  private Button backButton, forwardButton;

  @FXML
  public void initialize() {
    SceneController.setContentPane(contentPane);
    SceneController.setMainLayoutController(this);
    SceneController.setScene("Home.fxml");
    updateNavButtonsVisibility();
  }

  public void goBack(ActionEvent e) {
    SceneController.back();
    // SceneController.printViewLists();
  }

  public void goForward(ActionEvent e) {
    SceneController.forward();
    // SceneController.printViewLists();
  }

  public void updateNavButtonsVisibility() {
    backButton.setVisible(SceneController.viewList.size() > 1);
    forwardButton.setVisible(SceneController.visitedViewList.size() > 0);
  }
}
