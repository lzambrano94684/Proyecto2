package com.example.proyectoencuesta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @FXML
    Button btnMenu, btnActivar;

    // Metodos
    public void handMenu() throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("frmActivar.fxml"));
        Stage window = (Stage) btnActivar.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

}
