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

    private double ItemCurrentTotal_quantity;
    private int ItemQuantity;
    private double OrderTotal;
    private double OrderTotalTax;
    private boolean Processed;

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





    // Product Showcase
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
    private Label CustomerDisplay;
    @FXML
    private Label CashRegisterDisplay;
    @FXML
    private TextArea CustomerOrderReceipt;


    private Item tempProduct = new Item(-1, "N/a", "N/a", "n/a",
            0, 0, 0, false, 0);


    //***************************************************************************************
    @FXML
    protected void onItemID_click() throws URISyntaxException {

        boolean valid = false;
        for (Item product: productList) {
            if (product.getId() == (Integer.parseInt(EnteredItemID.getText()))) {

                valid = true;
                //Do the following if Item-ID valid
                ItemName.setText(product.getName());
                ItemDescription.setText(product.getDescription());
                ItemCurrentTotal.setText("$ " + product.getPrice());

                //Hide last product image
                switch (tempProduct.getId()) {
                    case 12 -> breadImg.setVisible(false);
                    case 18 -> alfredoSauceImg.setVisible(false);
                    case 28 -> eggsImg.setVisible(false);
                    case 34 -> chickenImg.setVisible(false);
                    case 35 -> beefImg.setVisible(false);
                    case 45 -> milkImg.setVisible(false);
                    case 55 -> riceImg.setVisible(false);
                    case 65 -> flourImg.setVisible(false);
                    case 75 -> jalapenoImg.setVisible(false);
                    case 85 -> pastaImg.setVisible(false);
                    case 90 -> avocadoImg.setVisible(false);
                    default -> {
                    }
                }

                //Show current product image
                switch (product.getId()) {
                    case 12 -> breadImg.setVisible(true);
                    case 18 -> alfredoSauceImg.setVisible(true);
                    case 28 -> eggsImg.setVisible(true);
                    case 34 -> chickenImg.setVisible(true);
                    case 35 -> beefImg.setVisible(true);
                    case 45 -> milkImg.setVisible(true);
                    case 55 -> riceImg.setVisible(true);
                    case 65 -> flourImg.setVisible(true);
                    case 75 -> jalapenoImg.setVisible(true);
                    case 85 -> pastaImg.setVisible(true);
                    case 90 -> avocadoImg.setVisible(true);
                    default -> {
                    }
                }

                tempProduct = product;
            }
        }

        //Else Item-ID is invalid
        if (!valid) {
            CustomerDisplay.setText("...");
            CashRegisterDisplay.setText("Invalid Item ID - "+EnteredItemID.getText()+"\nEnter another identification number...");
        }
    }

    //***************************************************************************************
    @FXML
    protected void onSetQuantity_click(){
        ItemQuantity = Integer.parseInt(EnteredItemQuantity.getText());
        ItemCurrentTotal_quantity = ItemQuantity * tempProduct.getPrice();

        ItemCurrentTotal.setText("$ " + String.valueOf(ItemCurrentTotal_quantity));
    }


    //***************************************************************************************
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