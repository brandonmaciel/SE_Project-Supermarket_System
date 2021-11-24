package com.example.se_projectsupermarket_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;



public class CheckoutOrderController {

    @FXML
    private TextArea CustomerDisplay;
    @FXML
    private TextArea CashRegisterDisplay;
    @FXML
    private TextArea CustomerOrderReceipt;



    //Used to transition from CheckoutOrder to MakePayment
    @FXML
    private javafx.scene.control.Button MakePaymentButton;

    @FXML
    private void MakePaymentButtonAction() throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) MakePaymentButton.getScene().getWindow();
        Stage new_stage = (Stage) MakePaymentButton.getScene().getWindow();
        // Transition to Make Payment Scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cashier_view.fxml"));
        Scene new_scene = new Scene(fxmlLoader.load(), 820, 740);
        new_stage.setTitle("Make Payment!");
        new_stage.setScene(new_scene);
        new_stage.show();
    }
}