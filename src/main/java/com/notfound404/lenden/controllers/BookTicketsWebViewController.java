package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class BookTicketsWebViewController {

  @FXML
  private WebView shohozWebView;

  @FXML
  private ImageView loaderImageView;

  public WebEngine webEngine;

  public WebHistory webHistory;

  @FXML
  private void initialize() {
    SceneController.setBookTicketsWebViewController(this);
    webEngine = shohozWebView.getEngine();
    webHistory = webEngine.getHistory();
    String url = "";

    if (SceneController.getCurrentView() == "BookTicketsBus.fxml") {
      url = "https://shohoz.com/bus-tickets";
    } else if (SceneController.getCurrentView() == "BookTicketsTrain.fxml") {
      url = "https://eticket.railway.gov.bd/";
    } else if (SceneController.getCurrentView() == "BookTicketsPlane.fxml") {
      url = "https://air.shohoz.com/";
    } else if (SceneController.getCurrentView() == "BookTicketsLaunch.fxml") {
      url = "https://shohoz.com/launch";
    }

    webEngine.setJavaScriptEnabled(true);
    webEngine.load(url);

    webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
        loaderImageView.setVisible(false);
        SceneController.setSceneLabel(webEngine.getTitle());
      } else {
        loaderImageView.setVisible(true);
      }
    });
  }

  public void goBack() {
    webHistory = webEngine.getHistory();
    webHistory.go(-1);
  }

  public void goForward() {
    webHistory = webEngine.getHistory();
    webHistory.go(1);
  }

}
