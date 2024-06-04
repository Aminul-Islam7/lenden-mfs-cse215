package com.notfound404.lenden.controllers;

import javafx.fxml.FXML;

public class BookTicketController {

    @FXML

  public void visitBookTicketBus() {
    SceneController.setScene("BookTicketBus.fxml", "Book Ticket - Bus");
  }

  public void visitBookTicketTrain() {
    SceneController.setScene("BookTicketTrain.fxml", "Book Ticket - Train");
  }

  public void visitBookTicketPlane() {
    SceneController.setScene("BookTicketPlane.fxml", "Book Ticket - Plane");
  }

  public void visitBookTicketLaunch() {
    SceneController.setScene("BookTicketLaunch.fxml", "Book Ticket - Launch");
  }

}