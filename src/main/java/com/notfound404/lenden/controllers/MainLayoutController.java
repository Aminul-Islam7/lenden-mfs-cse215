package com.notfound404.lenden.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Action;

public class MainLayoutController {

  @FXML
  private AnchorPane contentPane;

  @FXML
  public Button backButton;

  @FXML
  public void initialize() {
    SceneController.setContentPane(contentPane);
    SceneController.setMainLayoutController(this);
    SceneController.setScene("Home.fxml");
    updateBackButtonVisibility();
  }

  public void goBack(ActionEvent e) {
    SceneController.back();
  }

  public void updateBackButtonVisibility() {
    backButton.setVisible(SceneController.viewList.size() > 1);
  }

}
