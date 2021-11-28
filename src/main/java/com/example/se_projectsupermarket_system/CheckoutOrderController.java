package com.example.se_projectsupermarket_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

//*************************************************************************
public class CheckoutOrderController {

    private List<Item> productList = Data.items;
    private int currentOrderID;
    private Orders tmpOrder;
    private List<OrderItem> tmpOrderItems = new ArrayList<>();



    private boolean firstItemScan = false;
    private double OrderSubTotal = 0.00;
    private double OrderTotalTax;
    static double OrderTotal;       //Static

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
    @FXML
    private Label CurrentCustomer;


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
    protected void onItemID_click() throws URISyntaxException, IOException {

        boolean valid = false;
        for (Item product: productList) {
            if (product.getId() == (Integer.parseInt(EnteredItemID.getText()))) {

                valid = true;
                //Do the following if Item-ID valid
                ItemName.setText(product.getName());
                ItemDescription.setText(product.getDescription() + "\n    Weight - " + product.getWeight() + "\n    Discount - " + product.getDiscount());
                ItemCurrentTotal.setText("$ " + product.getPrice());

                if (product.getBulk() == true) {
                    ItemBulk.setVisible(true);
                    SCALE.setVisible(true);
                    EnteredBulkWeight.setVisible(true);
                    WeightSelected.setVisible(true);

                    SetQuantity.setVisible(false);
                    EnteredItemQuantity.setVisible(false);
                    QuantitySelected.setVisible(false);
                } else {
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

                //Manage Member's Account Window
                if (firstItemScan == false) {
                    firstItemScan = true;

                    //Pop-Up window for account on first item scan
                    Stage ACCstage = new Stage();
                    FXMLLoader FxmlLoader = new FXMLLoader(HelloApplication.class.getResource("manageAccount_view.fxml"));
                    Scene ACCscene = new Scene(FxmlLoader.load(), 500, 400);
                    ACCstage.setTitle("Manage Member's Account");
                    ACCstage.setScene(ACCscene);
                    ACCstage.show();
                }



                break;
            }
        }


        //Else Item-ID is invalid
        if (!valid) {
            CustomerDisplay.setText("...");
            CashRegisterDisplay.setText("Invalid Item ID - " + EnteredItemID.getText() + "\nEnter another identification number...");
        }

            //FOR CHECKING TO SEE IF SUCCESSFULLY ADDED ORDER
        /*
        for(Orders orders: Data.orders){
            CustomerOrderReceipt.appendText("\n****************************** ORDER #: ");
            CustomerOrderReceipt.appendText(orders.getId() +"\n");
            CustomerOrderReceipt.appendText(orders.getDate()+"\n");
            CustomerOrderReceipt.appendText(orders.getTime()+"\n");
            CustomerOrderReceipt.appendText(orders.getTotal() +"\n");
            CustomerOrderReceipt.appendText(orders.getTotal_Tax() +"\n");
            CustomerOrderReceipt.appendText(orders.getProcessed() +"\n");

            for(OrderItem items: orders.getItems()){
                CustomerOrderReceipt.appendText("\n\t"+ items.getId());
                CustomerOrderReceipt.appendText("\n\t"+items.getName());
                CustomerOrderReceipt.appendText("\n\t"+items.getDescription());
                CustomerOrderReceipt.appendText("\n\t"+items.getDiscount());
                CustomerOrderReceipt.appendText("\n\t"+ items.getWeight());
                CustomerOrderReceipt.appendText("\n\t"+ items.getPrice());
                CustomerOrderReceipt.appendText("\n\t"+ items.getQuantity());
            }

        }
        */

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


        //Removing item quantity from stock (Data.items)
        for(int i = 0; i < Data.items.size(); i++){
            if(tempProduct.getId() == Data.items.get(i).getId()){

                if(tempProduct.getBulk() == true){
                    int newQuantity = (Data.items.get(i).getQuantity() - 1);
                    Data.items.get(i).setQuantity(newQuantity);
                    System.out.println(newQuantity);
                }else{
                    int newQuantity = (Data.items.get(i).getQuantity() - ItemQuantity);
                    Data.items.get(i).setQuantity(newQuantity);
                    System.out.println(newQuantity);
                }

                break;
            }
        }


        //Setting Our Order Summary Labels
        OrderSubTotal = OrderSubTotal + ItemCurrentTotal_value;
        OrderTotalTax = (OrderSubTotal * 1.0825) - OrderSubTotal;

        subTotal.setText(String.format("$ %,.2f", OrderSubTotal));
        totalTax.setText(String.format("$ %,.2f", OrderTotalTax));

        // Initializing our current selected Item & quantity to an OrderItem Object to add to List<OrderItem>
        OrderItem tmpItem;

        if(tempProduct.getBulk()){
            //For bulk items; quantity is 1 while weight is different from product single weight
            tmpItem = new OrderItem(
                    tempProduct.getId(),
                    tempProduct.getName(),
                    tempProduct.getDescription(),
                    tempProduct.getDiscount(),
                    BulkWeight,
                    tempProduct.getPrice(),
                    1
            );
        }else{
            tmpItem = new OrderItem(
                    tempProduct.getId(),
                    tempProduct.getName(),
                    tempProduct.getDescription(),
                    tempProduct.getDiscount(),
                    tempProduct.getWeight(),
                    tempProduct.getPrice(),
                    ItemQuantity
            );
        }

        tmpOrderItems.add(tmpItem);


        //Setting our current customer label
        for(MembersAccount member: Data.members){
            if(member.getPin() == ManageMembershipController.member_pin && member.getPhone_num().contains(ManageMembershipController.member_phoneNum)) {
                CurrentCustomer.setText("Current Customer - "+member.getName()+
                                        "\nCredit Points - "+member.getCredit_points());
                break;
            }else{
                CurrentCustomer.setText("Current Customer - No account associated.");
            }
        }
    }

    //***************************************************************************************
    @FXML
    protected void onTOTAL_click(){

        OrderTotal = OrderSubTotal + OrderTotalTax;
        orderTotal.setText(String.format("$ %,.2f", OrderTotal));

        CustomerDisplay.setText("Total Price /w tax: \n"+String.format("\n$ %,.2f", OrderTotal));
        CashRegisterDisplay.setText("Total Price /w tax: \n"+String.format("\n$ %,.2f", OrderTotal)+"\nOrder Completed! Till opening...");

        CustomerOrderReceipt.appendText("\n*******************************************" +
                                        "\nSubTotal: "+"\t\t\t"+String.format("$ %,.2f", OrderSubTotal)+
                                        "\nTotal Tax:"+"\t\t\t"+String.format("$ %,.2f", OrderTotalTax)+
                                        "\nTotal Price:"+"\t\t\t"+String.format("$ %,.2f", OrderTotal));

        OrderCompleteOverlay.setVisible(true);
        PaymentNotReady.setVisible(true);
        paymentReady = true;
        PaymentNotReady.setText("Ready For Payment!");
        PaymentNotReady.setTextFill(Color.GREEN);



        //Initializing our temp Orders object to add to Data.orders
        currentOrderID = Data.orders.size() + 1;
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter Time = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();




        List<OrderItem> clonedOrderItems = new ArrayList<OrderItem>(tmpOrderItems);
        tmpOrder = new Orders(
                currentOrderID,
                Date.format(now),
                Time.format(now),
                ( Math.floor(100*OrderTotal) / 100 ),
                ( Math.floor(100*OrderTotalTax) / 100 ),
                false,
                clonedOrderItems
        );

        Data.orders.add(tmpOrder);

        //FOR CHECKING TO SEE IF SUCCESSFULLY ADDED ORDER
        /*
        for(Orders orders: Data.orders){
            CustomerOrderReceipt.appendText("\n****************************** ORDER #: ");
            CustomerOrderReceipt.appendText(orders.getId() +"\n");
            CustomerOrderReceipt.appendText(orders.getDate()+"\n");
            CustomerOrderReceipt.appendText(orders.getTime()+"\n");
            CustomerOrderReceipt.appendText(orders.getTotal() +"\n");
            CustomerOrderReceipt.appendText(orders.getTotal_Tax() +"\n");
            CustomerOrderReceipt.appendText(orders.getProcessed() +"\n");

            for(OrderItem items: orders.getItems()){
                CustomerOrderReceipt.appendText("\n\t"+ items.getId());
                CustomerOrderReceipt.appendText("\n\t"+items.getName());
                CustomerOrderReceipt.appendText("\n\t"+items.getDescription());
                CustomerOrderReceipt.appendText("\n\t"+items.getDiscount());
                CustomerOrderReceipt.appendText("\n\t"+ items.getWeight());
                CustomerOrderReceipt.appendText("\n\t"+ items.getPrice());
                CustomerOrderReceipt.appendText("\n\t"+ items.getQuantity());
            }
        }
        */




        //Add credit points for Verified Members
        ManageMembershipController.addCreditPoints();

        //Add Current Order ID to Data.members; current Member's orders_id integer array;
        ManageMembershipController.addOrderID(currentOrderID);

        //Setting our current customer label with calculated new added points
        for(MembersAccount member: Data.members){
            if(member.getPin() == ManageMembershipController.member_pin && member.getPhone_num().contains(ManageMembershipController.member_phoneNum)) {
                CurrentCustomer.setText("Current Customer - "+member.getName()+
                        "\nNew Credit Points - "+member.getCredit_points() +
                        "\nPoints Added - "+String.valueOf(Math.round( OrderTotal * 0.10)) );
                break;
            }
        }
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

            //Erase Locally data for next checkout order
            firstItemScan = false;
            paymentReady = false;
            tmpOrderItems.clear();
            OrderTotal = 0.00;
            OrderTotalTax = 0.00;
            OrderSubTotal = 0.00;

            OrderCompleteOverlay.setVisible(false);
            PaymentNotReady.setVisible(false);
            PaymentNotReady.setText("TOTAL must be calculated first!");
            PaymentNotReady.setTextFill(Color.RED);

            //Set UI for next checkout order
            CustomerOrderReceipt.clear();
            CustomerDisplay.setText("...");
            CashRegisterDisplay.setText("TILL is closed\nWaiting for Item-ID...");


            EnteredItemID.clear();
            EnteredItemQuantity.clear();
            EnteredBulkWeight.clear();

            subTotal.setText("$ 0.00");
            totalTax.setText("$ 0.00");
            orderTotal.setText("$ 0.00");
            ItemCurrentTotal.setText("$ 0.00");
            ItemDescription.setText("...");
            ItemName.setText("Item Name");
            QuantitySelected.setText("Quantity Selected - ");
            WeightSelected.setText("Weight Selected - ");
            CurrentCustomer.setText("Current Customer - ");

            //Hide any product showcase
            breadImg.setVisible(false);
            alfredoSauceImg.setVisible(false);
            eggsImg.setVisible(false);
            chickenImg.setVisible(false);
            beefImg.setVisible(false);
            milkImg.setVisible(false);
            riceImg.setVisible(false);
            flourImg.setVisible(false);
            jalapenoImg.setVisible(false);
            pastaImg.setVisible(false);
            avocadoImg.setVisible(false);



            //Pop-Up window for Payment Window
            Stage PaymentStage = new Stage();
            FXMLLoader FxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cashier_view.fxml"));
            Scene PaymentScene = new Scene(FxmlLoader.load(), 820, 740);
            PaymentStage.setTitle("Cashier View");
            PaymentStage.setScene(PaymentScene);
            PaymentStage.show();
        }
    }
}