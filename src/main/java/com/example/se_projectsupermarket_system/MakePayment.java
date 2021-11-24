package com.example.se_projectsupermarket_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MakePayment extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cashier_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 740);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/data/bank.json"));

            // convert JSON array to list of books
            List<Bank> books = Arrays.asList(gson.fromJson(reader, Bank[].class));

            // print books
            books.forEach(System.out::println);

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        launch();
    }
}