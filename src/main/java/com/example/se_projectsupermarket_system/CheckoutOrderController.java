package com.example.se_projectsupermarket_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;



public class CheckoutOrderController {

    private List<Item> productList = Data.items;
    private int currentOrderIndex = Data.currentOrderIndex;

    @FXML
    private TextField EnteredItemID;
    @FXML
    private TextField EnteredItemQuantity;
    @FXML
    private Label ItemName;
    @FXML
    private Label ItemDescription;
    @FXML
    private Label ItemCurrentTotal;

    @FXML
    private ImageView breadImg;
    @FXML
    private ImageView alfredoSauceImg;
    @FXML
    private ImageView eggsImg;
    @FXML
    private ImageView chickenImg;
    @FXML
    private ImageView beefImg;
    @FXML
    private ImageView milkImg;
    @FXML
    private ImageView riceImg;
    @FXML
    private ImageView flourImg;
    @FXML
    private ImageView jalapenoImg;
    @FXML
    private ImageView pastaImg;
    @FXML
    private ImageView avocadoImg;




    @FXML
    private TextArea CustomerDisplay;
    @FXML
    private TextArea CashRegisterDisplay;
    @FXML
    private TextArea CustomerOrderReceipt;




    @FXML
    protected void onItemID_click() throws URISyntaxException {


        // Doesn't check for empty strings; that's above my pay grade
        boolean valid = false;
        for (Item product: Data.items) {
            if(product.getId() == (Integer.parseInt(EnteredItemID.getText()))){
                CustomerOrderReceipt.appendText("ITEM ID VALID\n");
                //CustomerOrderReceipt.appendText(String.valueOf(currentOrderIndex));
                valid = true;

                //Do the following if Item-ID valid
                //ProductImage.setVisible(false);
                ItemName.setText(product.getName());
                ItemDescription.setText(product.getDescription());

                break;
            }
        }

        //Else Item-ID is invalid
        if(valid == false){
            CustomerOrderReceipt.appendText("ITEM ID nooooooooooooo\n");
        }


        return;
    }




    //Used to transition from CheckoutOrder to MakePayment
    @FXML
    private javafx.scene.control.Button MakePaymentButton;

    @FXML
    private void MakePaymentButtonAction() throws IOException {
        // get a handle to the stage

        //Stage stage = (Stage) MakePaymentButton.getScene().getWindow();
        //Stage new_stage = (Stage) MakePaymentButton.getScene().getWindow();
        // Transition to Make Payment Scene
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cashier_view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 820, 740);
        //stage.setTitle("Cashier View");
        //stage.setScene(scene);
        //stage.show();
    }
}