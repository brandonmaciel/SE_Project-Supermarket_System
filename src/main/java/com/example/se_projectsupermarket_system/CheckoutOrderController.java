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
    private TextArea CustomerDisplay;
    @FXML
    private TextArea CashRegisterDisplay;
    @FXML
    private TextArea CustomerOrderReceipt;


    Item tempLastProduct = new Item(-1, "N/a", "N/a", "n/a",
            0, 0, 0, false, 0);

    //***************************************************************************************
    @FXML
    protected void onItemID_click() throws URISyntaxException {

        boolean valid = false;

        for (Item product: productList) {
            if (product.getId() == (Integer.parseInt(EnteredItemID.getText()))) {
                valid = true;
                
                CustomerOrderReceipt.appendText("ITEM ID VALID\n");
                //CustomerOrderReceipt.appendText(String.valueOf(currentOrderIndex));

                //Do the following if Item-ID valid
                ItemName.setText(product.getName());
                ItemDescription.setText(product.getDescription());

                //Hide last product image
                switch (tempLastProduct.getId()) {
                    case 12:
                        breadImg.setVisible(false);
                        break;
                    case 18:
                        alfredoSauceImg.setVisible(false);
                        break;
                    case 28:
                        eggsImg.setVisible(false);
                        break;
                    case 34:
                        chickenImg.setVisible(false);
                        break;
                    case 35:
                        beefImg.setVisible(false);
                        break;
                    case 45:
                        milkImg.setVisible(false);
                        break;
                    case 55:
                        riceImg.setVisible(false);
                        break;
                    case 65:
                        flourImg.setVisible(false);
                        break;
                    case 75:
                        jalapenoImg.setVisible(false);
                        break;
                    case 85:
                        pastaImg.setVisible(false);
                        break;
                    case 90:
                        avocadoImg.setVisible(false);
                        break;
                    default:
                        break;
                }

                //Show current product image
                switch (product.getId()) {
                    case 12:
                        breadImg.setVisible(true);
                        break;
                    case 18:
                        alfredoSauceImg.setVisible(true);
                        break;
                    case 28:
                        eggsImg.setVisible(true);
                        break;
                    case 34:
                        chickenImg.setVisible(true);
                        break;
                    case 35:
                        beefImg.setVisible(true);
                        break;
                    case 45:
                        milkImg.setVisible(true);
                        break;
                    case 55:
                        riceImg.setVisible(true);
                        break;
                    case 65:
                        flourImg.setVisible(true);
                        break;
                    case 75:
                        jalapenoImg.setVisible(true);
                        break;
                    case 85:
                        pastaImg.setVisible(true);
                        break;
                    case 90:
                        avocadoImg.setVisible(true);
                        break;
                    default:
                        break;
                }

                tempLastProduct = product;
            }
        }

        //Else Item-ID is invalid
        if (valid == false) {
            CustomerOrderReceipt.appendText("ITEM ID nooooooooooooo\n");
        }


        return;
        
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