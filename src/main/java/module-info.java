module com.example.se_projectsupermarket_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.se_projectsupermarket_system to javafx.fxml;
    exports com.example.se_projectsupermarket_system;
}