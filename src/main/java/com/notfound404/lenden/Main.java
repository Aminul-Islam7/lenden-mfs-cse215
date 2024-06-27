package com.notfound404.lenden;

import java.io.File;
import java.io.IOException;

import com.notfound404.lenden.controllers.SceneController;

import javafx.application.Application;
import javafx.stage.Stage;

import com.notfound404.lenden.models.User;
import com.notfound404.lenden.services.UserService;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        createDataFolder();
        SceneController.setStages();
        UserService userService = new UserService();
        User currentUser = userService.getCurrentUser();
        if (currentUser != null)
            SceneController.showMainStage();
        else
            SceneController.showAuthenticationStage();
    }

    private static void createDataFolder() {
        String dataFolderPath = System.getProperty("user.home") + "/Lenden/data";
        File dataFolder = new File(dataFolderPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

}
