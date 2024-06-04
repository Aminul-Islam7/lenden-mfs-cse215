package com.notfound404.lenden.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SceneController {
  private static Stage authenticationStage;
  private static Stage mainStage;
  private static Stage currentStage;
  private static Stage secondaryStage;

  private static AnchorPane contentPane;
  public static ArrayList<String> viewList = new ArrayList<>();
  public static ArrayList<String> visitedViewList = new ArrayList<>();
  private static HashMap<String, String> sceneLabels = new HashMap<>();

  private static MainLayoutController mainLayoutController;

  public static Stage getStage() {
    return currentStage;
  }

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

  public static String getCurrentView() {
    return viewList.get(viewList.size() - 1);
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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void setStages() {
    authenticationStage = new Stage();
    mainStage = new Stage();

    try {
      Parent authenticationRoot = FXMLLoader.load(SceneController.class.getResource("/fxml/Login.fxml"));
      Scene authenticationScene = new Scene(authenticationRoot);

      String css = SceneController.class.getResource("/css/style.css").toExternalForm();
      authenticationScene.getStylesheets().add(css);

      Image icon = new Image(SceneController.class.getResource("/images/icon1.png").toExternalForm());
      authenticationStage.getIcons().add(icon);

      authenticationStage.setTitle("Lenden - A Simulated Mobile Financial System");

      authenticationStage.setResizable(false);

      authenticationStage.setScene(authenticationScene);

      Parent mainRoot = FXMLLoader.load(SceneController.class.getResource("/fxml/MainLayout.fxml"));
      Scene mainScene = new Scene(mainRoot);

      mainScene.getStylesheets().add(css);

      mainStage.getIcons().add(icon);

      mainStage.setTitle("Lenden - A Simulated Mobile Financial System");

      mainStage.setResizable(false);

      mainStage.setScene(mainScene);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void showAuthenticationStage() {
    if (mainStage.isShowing()) {
      mainStage.close();
    }
    currentStage = authenticationStage;
    authenticationStage.show();
  }

  public static void showMainStage() {
    if (authenticationStage.isShowing()) {
      authenticationStage.close();
    }

    currentStage = mainStage;
    mainStage.show();
  }

  public static void switchToScene(String string) {
    try {
      Scene scene = new Scene(FXMLLoader.load(SceneController.class.getResource("/fxml/" + string)));
      currentStage.setScene(scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void closeSecondaryStage() {
    secondaryStage.close();
  }

}
