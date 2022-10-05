module com.example.cuestionarioapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cuestionarioapp to javafx.fxml;
    exports com.example.cuestionarioapp;
}