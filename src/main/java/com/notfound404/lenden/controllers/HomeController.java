package com.notfound404.lenden.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Action;

public class HomeController {

  @FXML
  private Label label;

  public void loadContent(ActionEvent e) {
    SceneController.setScene("Content.fxml");
  }

}
