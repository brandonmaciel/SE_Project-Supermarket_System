package com.example.se_projectsupermarket_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class CheckoutOrder extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("checkout_view.fxml" ));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Checkout Order");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws IOException{
        Data.parseFiles();
        launch();
    }
}
