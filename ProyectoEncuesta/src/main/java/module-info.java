module com.example.proyectoencuesta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoencuesta to javafx.fxml;
    exports com.example.proyectoencuesta;
}