package com.test.mobilesmart.Client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("StartScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Mobile SMARTS");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            MenuSceneController.stop();
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}