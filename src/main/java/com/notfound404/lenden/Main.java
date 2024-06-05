package com.notfound404.lenden;

import java.io.IOException;

import com.notfound404.lenden.controllers.SceneController;

import javafx.application.Application;
import javafx.stage.Stage;

import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.UserService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneController.setStages();
        UserService userService = new UserService();
        User currentUser = userService.getCurrentUser();
        if (currentUser != null)
            SceneController.showMainStage();
        else
            SceneController.showAuthenticationStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
