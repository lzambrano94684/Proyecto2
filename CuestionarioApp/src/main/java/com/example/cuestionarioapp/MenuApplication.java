package com.example.cuestionarioapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuApplication extends Application {

    @Override
    public void start(Stage primarySatage) throws Exception {
        Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        primarySatage.setTitle("Cuestionario App");
        Scene scene = new Scene(root, 750, 400);
        primarySatage.setScene(scene);
        primarySatage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}