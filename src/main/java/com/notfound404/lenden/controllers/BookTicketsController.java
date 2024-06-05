package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;

public class BookTicketsController {

  @FXML

  public void visitBookTicketsBus() {
    SceneController.setScene("BookTicketsBus.fxml", "Book Tickets - Bus");
  }

  public void visitBookTicketsTrain() {
    SceneController.setScene("BookTicketsTrain.fxml", "Book Tickets - Train");
  }

  public void visitBookTicketsPlane() {
    SceneController.setScene("BookTicketsPlane.fxml", "Book Tickets - Plane");
  }

  public void visitBookTicketsLaunch() {
    SceneController.setScene("BookTicketsLaunch.fxml", "Book Tickets - Launch");
  }

}