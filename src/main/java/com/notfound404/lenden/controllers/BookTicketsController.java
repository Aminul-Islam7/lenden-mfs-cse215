package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;

public class BookTicketsController {

  @FXML
  private void visitBookTicketsBus() {
    SceneController.setScene("BookTicketsBus.fxml", "Book Tickets - Bus");
  }

  @FXML
  private void visitBookTicketsTrain() {
    SceneController.setScene("BookTicketsTrain.fxml", "Book Tickets - Train");
  }

  @FXML
  private void visitBookTicketsPlane() {
    SceneController.setScene("BookTicketsPlane.fxml", "Book Tickets - Plane");
  }

  @FXML
  private void visitBookTicketsLaunch() {
    SceneController.setScene("BookTicketsLaunch.fxml", "Book Tickets - Launch");
  }

}