package com.example.se_projectsupermarket_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class MakePayment extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cashier_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 740);
        stage.setTitle("Cashier View");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {



//        launch();
    }
}