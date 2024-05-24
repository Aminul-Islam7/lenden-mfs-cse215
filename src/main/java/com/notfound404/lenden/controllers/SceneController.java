package com.notfound404.lenden.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class SceneController {

  private static AnchorPane contentPane;
  public static ArrayList<String> viewList = new ArrayList<>();
  public static ArrayList<String> visitedViewList = new ArrayList<>();

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
      loadScene(viewList.get(viewList.size() - 1));
    }
  }

  public static void forward() {
    if (visitedViewList.size() > 0) {
      String nextView = visitedViewList.remove(visitedViewList.size() - 1);
      viewList.add(nextView);
      loadScene(nextView);
    }
  }

  public static void setScene(String fxmlFile) {
    if (!viewList.isEmpty() && !viewList.get(viewList.size() - 1).equals(fxmlFile)) {
      viewList.add(fxmlFile);
    } else if (viewList.isEmpty()) {
      viewList.add(fxmlFile);
    }
    visitedViewList.clear();
    loadScene(fxmlFile);
  }

  private static void loadScene(String fxmlFile) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(SceneController.class.getResource("/fxml/" + fxmlFile));
      AnchorPane view = loader.load();
      contentPane.getChildren().setAll(view);
      mainLayoutController.updateNavButtonsVisibility();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // public static void printViewLists() {
  // System.out.println("View list:");
  // for (String view : viewList) {
  // System.out.println(view);
  // }
  // System.out.println("Visited views:");
  // for (String view : visitedViewList) {
  // System.out.println(view);
  // }
  // }
}
