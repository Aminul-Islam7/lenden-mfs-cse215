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
import java.util.Stack;

import javax.swing.Action;

public class SceneController {

  private static AnchorPane contentPane;
  public static ArrayList<String> viewList = new ArrayList<>();

  private static MainLayoutController mainLayoutController;

  public static void setContentPane(AnchorPane contentPane) {
    SceneController.contentPane = contentPane;
  }

  public static void setMainLayoutController(MainLayoutController controller) {
    SceneController.mainLayoutController = controller;
  }

  public static void back() {
    if (viewList.size() > 1) {
      viewList.remove(viewList.size() - 1);
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneController.class.getResource("/fxml/" + viewList.get(viewList.size() - 1)));
        AnchorPane view = loader.load();
        contentPane.getChildren().setAll(view);
        mainLayoutController.updateBackButtonVisibility();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void setScene(String fxmlFile) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(SceneController.class.getResource("/fxml/" + fxmlFile));
      AnchorPane view = loader.load();
      contentPane.getChildren().setAll(view);
      viewList.add(fxmlFile);
      mainLayoutController.updateBackButtonVisibility();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
