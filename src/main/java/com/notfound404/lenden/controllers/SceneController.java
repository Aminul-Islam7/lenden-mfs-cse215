package com.notfound404.lenden.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SceneController {

  private static AnchorPane contentPane;
  public static ArrayList<String> viewList = new ArrayList<>();
  public static ArrayList<String> visitedViewList = new ArrayList<>();
  private static HashMap<String, String> sceneLabels = new HashMap<>();

  private static MainLayoutController mainLayoutController;

  public static void setContentPane(AnchorPane contentPane) {
    SceneController.contentPane = contentPane;
  }

  public static void setMainLayoutController(MainLayoutController controller) {
    SceneController.mainLayoutController = controller;
  }

  public static void back() {
    if (viewList.size() > 1) {
      String currentView = viewList.remove(viewList.size() - 1);
      visitedViewList.add(currentView);
      String previousView = viewList.get(viewList.size() - 1);
      loadScene(previousView, sceneLabels.get(previousView));
    }
  }

  public static void forward() {
    if (visitedViewList.size() > 0) {
      String nextView = visitedViewList.remove(visitedViewList.size() - 1);
      viewList.add(nextView);
      loadScene(nextView, sceneLabels.get(nextView));
    }
  }

  public static void setScene(String fxmlFile, String sceneLabel) {
    if (!viewList.isEmpty() && !viewList.get(viewList.size() - 1).equals(fxmlFile)) {
      viewList.add(fxmlFile);
    } else if (viewList.isEmpty()) {
      viewList.add(fxmlFile);
    }
    visitedViewList.clear();
    sceneLabels.put(fxmlFile, sceneLabel);
    loadScene(fxmlFile, sceneLabel);
  }

  private static void loadScene(String fxmlFile, String sceneLabel) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(SceneController.class.getResource("/fxml/" + fxmlFile));
      AnchorPane view = loader.load();
      contentPane.getChildren().setAll(view);
      mainLayoutController.setSceneLabel(sceneLabel);
      mainLayoutController.updateNavButtonsVisibility();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
