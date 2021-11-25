package com.example.se_projectsupermarket_system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.net.URISyntaxException;
import java.util.List;

import java.io.IOException;


public class CheckoutOrderController {

    private List<Item> productList = Data.items;

    private int currentOrderIndex = Data.currentOrderIndex;
    private String OrderDate;
    private String OrderTime;
    private double OrderSubTotal = 0.00;
    private double OrderTotalTax;
    private double OrderTotal;

    private double ItemCurrentTotal_value;
    private int ItemQuantity;
    private double BulkWeight;
    private boolean paymentReady = false;



    @FXML
    private TextField EnteredItemID;
    @FXML
    private TextField EnteredItemQuantity;
    @FXML
    private TextField EnteredBulkWeight;
    @FXML
    private Button SetQuantity;
    @FXML
    private Button SCALE;
    @FXML
    private Button ITEM_ID;
    @FXML
    private Button TOTAL;
    @FXML
    private Label ItemName;
    @FXML
    private Label ItemDescription;
    @FXML
    private Label ItemBulk;
    @FXML
    private Label WeightSelected;
    @FXML
    private Label QuantitySelected;
    @FXML
    private Label ItemCurrentTotal;
    @FXML
    private ImageView OrderCompleteOverlay;
    @FXML
    private Label PaymentNotReady;


    //Order Summary Labels
    @FXML
    private Label subTotal;
    @FXML
    private Label totalTax;
    @FXML
    private Label orderTotal;





    // Product Image Showcase
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



    // Our Three Displays
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
                ItemDescription.setText(product.getDescription()+"\nWeight - "+product.getWeight()+"\nDiscount - "+product.getDiscount());
                ItemCurrentTotal.setText("$ " + product.getPrice());

                if(product.getBulk() == true){
                    ItemBulk.setVisible(true);
                    SCALE.setVisible(true);
                    EnteredBulkWeight.setVisible(true);
                    WeightSelected.setVisible(true);

                    SetQuantity.setVisible(false);
                    EnteredItemQuantity.setVisible(false);
                    QuantitySelected.setVisible(false);
                }else{
                    ItemBulk.setVisible(false);
                    SCALE.setVisible(false);
                    EnteredBulkWeight.setVisible(false);
                    WeightSelected.setVisible(false);

                    SetQuantity.setVisible(true);
                    EnteredItemQuantity.setVisible(true);
                    QuantitySelected.setVisible(true);
                }

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
        ItemCurrentTotal_value = ItemQuantity * tempProduct.getPrice();

        QuantitySelected.setText("Quantity Selected - "+ItemQuantity);
        ItemCurrentTotal.setText("$ " + String.format("%,.2f", ItemCurrentTotal_value));
    }

    //***************************************************************************************
    @FXML
    protected void onSetWeight_click(){
        ItemQuantity = 1;
        BulkWeight = Double.parseDouble(EnteredBulkWeight.getText());
        ItemCurrentTotal_value = (BulkWeight/tempProduct.getWeight()) * tempProduct.getPrice();

        WeightSelected.setText("Weight Selected - "+BulkWeight);
        ItemCurrentTotal.setText("$ " + String.format("%,.2f", ItemCurrentTotal_value));
    }

    //***************************************************************************************
    @FXML
    protected void onAddToOrder_click(){

        //To Display to the three displays
        if(tempProduct.getBulk()){
            CustomerOrderReceipt.appendText(tempProduct.getName()+"\n   [ Weight ] - "+
                                            BulkWeight+"\t\t\t\t$ "+String.format("%,.2f", ItemCurrentTotal_value)+"\n");

            CustomerDisplay.setText("$ "+String.format("%,.2f", ItemCurrentTotal_value)+
                                    "\n"+tempProduct.getName()+" [Weight] - "+BulkWeight+" lbs"+
                                    "\n\t"+tempProduct.getDescription()+
                                    "\n\t"+tempProduct.getDiscount());
            CashRegisterDisplay.setText("$ "+String.format("%,.2f", ItemCurrentTotal_value)+
                                        "\n"+tempProduct.getName()+" [Weight] - "+BulkWeight+" lbs"+
                                        "\n\t"+tempProduct.getDescription()+
                                        "\n\t"+tempProduct.getDiscount());
        }else{
            CustomerOrderReceipt.appendText(tempProduct.getName()+"\n   [ Quantity ] - "+
                                            ItemQuantity+"\t\t\t\t$ "+String.format("%,.2f", ItemCurrentTotal_value)+"\n");

            CustomerDisplay.setText("$ "+String.format("%,.2f", ItemCurrentTotal_value)+
                                    "\n"+tempProduct.getName()+" [Quantity] - "+ItemQuantity+
                                    "\n\t"+tempProduct.getDescription()+
                                    "\n\t"+tempProduct.getDiscount());
            CashRegisterDisplay.setText("$ "+String.format("%,.2f", ItemCurrentTotal_value)+
                                        "\n"+tempProduct.getName()+" [Quantity] - "+ItemQuantity+
                                        "\n\t"+tempProduct.getDescription()+
                                        "\n\t"+tempProduct.getDiscount());
        }

        //Setting Our Order Summary Labels
        OrderSubTotal = OrderSubTotal + ItemCurrentTotal_value;
        OrderTotalTax = (OrderSubTotal * 1.0825) - OrderSubTotal;

        subTotal.setText(String.format("$ %,.2f", OrderSubTotal));
        totalTax.setText(String.format("$ %,.2f", OrderTotalTax));
    }

    //***************************************************************************************
    @FXML
    protected void onTOTAL_click(){

        OrderTotal = OrderSubTotal + OrderTotalTax;
        orderTotal.setText(String.format("$ %,.2f", OrderTotal));

        CustomerDisplay.setText("Total Price /w tax: \n"+String.format("\n$ %,.2f", OrderTotal));
        CashRegisterDisplay.setText("Total Price /w tax: \n"+String.format("\n$ %,.2f", OrderTotal)+"\nOrder Completed! Till opening...");

        CustomerOrderReceipt.appendText("\n****************************" +
                                        "\nSubTotal: "+"\t\t\t"+String.format("$ %,.2f", OrderSubTotal)+
                                        "\nTotal Tax:"+"\t\t\t"+String.format("$ %,.2f", OrderTotalTax)+
                                        "\nTotal Price:"+"\t\t\t"+String.format("$ %,.2f", OrderTotal));

        OrderCompleteOverlay.setVisible(true);
        PaymentNotReady.setVisible(true);
        PaymentNotReady.setText("Ready For Payment!");
        PaymentNotReady.setTextFill(Color.GREEN);
    }






    //***************************************************************************************
    @FXML
    protected void ItemIDHover(){
        ITEM_ID.setTextFill(Color.GREEN);
        ITEM_ID.setFont(new Font("Segoe UI Semibold",12));
    }
    @FXML
    protected void ItemIDHoverExit(){
        ITEM_ID.setTextFill(Color.BLACK);
        ITEM_ID.setFont(new Font("Segoe UI Semibold",10));
    }

    //***************************************************************************************
    @FXML
    protected void TOTALhover(){
        TOTAL.setTextFill(Color.GREEN);
        TOTAL.setFont(new Font("Segoe UI Semibold",19));
    }
    @FXML
    protected void TOTALhoverExit(){
        TOTAL.setTextFill(Color.BLACK);
        TOTAL.setFont(new Font("Segoe UI Semibold",17));
    }

    //***************************************************************************************
    @FXML
    protected void MakePaymentHover(){
        MakePaymentButton.setFont(new Font("Segoe UI Semibold",18));
    }
    @FXML
    protected void MakePaymentHoverExit(){
        MakePaymentButton.setFont(new Font("Segoe UI Semibold",17));
    }

    //***************************************************************************************
    //Used to transition from CheckoutOrder to MakePayment
    @FXML
    private javafx.scene.control.Button MakePaymentButton;

    @FXML
    private void MakePaymentButtonAction() throws IOException {

        if(paymentReady == false){
            PaymentNotReady.setVisible(true);
        }else{

        }
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