package com.notfound404.lenden.controllers;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WithdrawLocationsController {

  @FXML
  private WebView mapWebView;
  private WebEngine engine;

  @FXML
  private void initialize() {
    engine = mapWebView.getEngine();
    URL url = getClass().getResource("/com/notfound404/lenden/views/withdraw_locations.html");

    engine.setJavaScriptEnabled(true);

    engine.load(url.toExternalForm());

  }

}
