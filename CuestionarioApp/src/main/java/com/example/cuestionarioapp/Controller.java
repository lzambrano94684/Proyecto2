package com.example.cuestionarioapp;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    Button btnAgregar, btnScene2;

    // methods

    public void handleBtnAgregar() throws Exception {
        //Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FrmCuestionario.fxml")));
        Stage primaryStage = (Stage) btnAgregar.getScene().getWindow();
        //primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.setTitle("Nuevo Cuestionario");
//Header
        Text txtHeader = new Text("Mantenimiento de Preguntas");
        txtHeader.setFont(Font.font(20));
        txtHeader.setFill(Color.GREEN);

        //TableView + ObservableList
        TableView<JavaFX8TableView.SoccerTeam> teamTable = new TableView<>();
        ObservableList<JavaFX8TableView.SoccerTeam> teams = FXCollections.observableArrayList(
                new JavaFX8TableView.SoccerTeam("Pregunta 1", "20", "5"),
                new JavaFX8TableView.SoccerTeam("Pregunta 2", "15", "10"),
                new JavaFX8TableView.SoccerTeam("Pregunta 3", "10", "15"),
                new JavaFX8TableView.SoccerTeam("Pregunta 4", "5", "5")
        );

        teamTable.setItems(teams);
        teamTable.setMaxSize(500, 600);
        teamTable.setMinSize(250, 300);

        //Set TableColumns
        TableColumn colClubInfo = new TableColumn("Información");

        TableColumn<JavaFX8TableView.SoccerTeam, String> colName = new TableColumn<>("Pregunta");
        colName.setCellValueFactory(new PropertyValueFactory<JavaFX8TableView.SoccerTeam, String>("name"));
        colName.setMinWidth(teamTable.getMaxWidth()/4);


        TableColumn<JavaFX8TableView.SoccerTeam, String> colCountry = new TableColumn<>("Tiempo Limite");
        colCountry.setCellValueFactory(new PropertyValueFactory<JavaFX8TableView.SoccerTeam, String>("country"));
        colCountry.setMinWidth(teamTable.getMaxWidth()/4);

        TableColumn<JavaFX8TableView.SoccerTeam, String> colStadium = new TableColumn<>("Punteo");
        colStadium.setCellValueFactory(new PropertyValueFactory<JavaFX8TableView.SoccerTeam, String>("stadium"));
        colStadium.setMinWidth(teamTable.getMaxWidth()/4);



        //Asign Columns to TableView
        colClubInfo.getColumns().addAll(colName, colCountry, colStadium);
        teamTable.getColumns().addAll(colClubInfo);

        //Nodes
        Text txtName = new Text("Pregunta");
        Text txtCountry = new Text("Tiempo Limite");
        Text txtStadium = new Text("Punteo");
        Text txtNotification = new Text("Notifications");
        txtNotification.setFont(Font.font(20));
        txtNotification.setFill(Color.BLUE);

        TextField fldName = new TextField();
        TextField fldCountry = new TextField();
        TextField fldStadium = new TextField();

        Button btnAdd = new Button("Agregar");
        btnAdd.setMinWidth(85);

        //Add Information to TableView
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if(fldName.getText().isEmpty() || fldCountry.getText().isEmpty() || fldStadium.getText().isEmpty()){
                    txtNotification.setText("Please Add information to all the fields");
                }else{
                    teams.add(new JavaFX8TableView.SoccerTeam(
                            fldName.getText(),
                            fldCountry.getText(),
                            fldStadium.getText()));

                }


            }
        });

        Button btnReplace = new Button("Modificar");
        btnReplace.setMinWidth(85);

        //Replace Information in TableView
        btnReplace.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if(teamTable.getSelectionModel().isEmpty() ||
                        (fldName.getText().isEmpty() && fldCountry.getText().isEmpty() && fldStadium.getText().isEmpty() )){

                    txtNotification.setText("Please Add information to all the fields and Select an Item in the Table");

                }else{
                    String strName = teams.get(teamTable.getSelectionModel().getSelectedIndex()).getName();
                    String strCountry = teams.get(teamTable.getSelectionModel().getSelectedIndex()).getCountry();
                    String strStadium = teams.get(teamTable.getSelectionModel().getSelectedIndex()).getStadium();
                    if(!fldName.getText().isEmpty()){
                        strName = fldName.getText();
                    }
                    if(!fldCountry.getText().isEmpty()){
                        strCountry = fldCountry.getText();
                    }
                    if(!fldStadium.getText().isEmpty()){
                        strStadium = fldStadium.getText();
                    }

                    teams.set(teamTable.getSelectionModel().getSelectedIndex(),
                            new JavaFX8TableView.SoccerTeam(strName, strCountry, strStadium));
                }
            }
        });

        Button btnRemove = new Button("Eliminar");
        btnRemove.setMinWidth(85);

        //Remove Row from TableView
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if(teamTable.getSelectionModel().isEmpty()){
                    txtNotification.setText("Please Select an Item from the List");
                }else{
                    teams.remove(teamTable.getSelectionModel().getSelectedItem());
                }

            }
        });

        //Adding Change Listener to TableView
        teams.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change change) {

                txtNotification.setText("TableView Successfully changed");
            }
        });

        //Layouts
        GridPane center = new GridPane();
        center.setHgap(10);
        center.setVgap(10);
        center.setPadding(new Insets(50));

        center.add(txtHeader, 0, 0, 5, 1);
        GridPane.setHalignment(txtHeader, HPos.CENTER);
        center.add(teamTable, 0, 1, 5, 5);

        center.add(txtName, 0, 7);
        center.add(txtCountry, 1, 7);
        center.add(txtStadium, 2, 7);

        center.add(fldName, 0, 8);
        center.add(fldCountry, 1, 8);
        center.add(fldStadium, 2, 8);

        VBox right = new VBox(15);
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(5, 100,5,5));

        right.getChildren().addAll(btnAdd, btnReplace, btnRemove);

        BorderPane root = new BorderPane();

        root.setCenter(center);
        root.setRight(right);
        root.setBottom(txtNotification);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("Cuestionario");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void handleBtn2() throws Exception {
        Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage window = (Stage) btnScene2.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }
}
