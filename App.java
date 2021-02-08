package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mess.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Чатъ");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
