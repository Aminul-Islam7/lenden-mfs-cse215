package com.notfound404.lenden;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainLayout.fxml"));
        Scene scene = new Scene(root);

        String css = this.getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image("/images/icon1.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Lenden - Simulated Mobile Financial System");

        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
