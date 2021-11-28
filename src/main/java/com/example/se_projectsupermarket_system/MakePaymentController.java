package com.example.se_projectsupermarket_system;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MakePaymentController {

    private boolean paymentSelected = false;
    private boolean finishScanning = false;
    private boolean orderCompleted = true;
    private int currentOrderIndex = Data.currentOrderIndex; //Maybe Data.orders.getSize()?
    private List<OrderItem> itemList = Data.orders.get(currentOrderIndex).getItems();
    private int itemIndex = 0;



    @FXML
    private TextArea console;
    @FXML
    private TextArea inventoryMessages;
    @FXML
    private Pane inventoryPane;
    @FXML
    private Button OKshutdownButton;



    @FXML
    protected void onCashClick() {

        if (paymentSelected) {
            console.appendText("Payment already selected.\n");
            return;
        }

        console.appendText("Cash selected!\n");
        paymentSelected = true;
    }

    @FXML
    protected void onCardClick() {

        if (paymentSelected) {
            console.appendText("Payment already selected.\n");
            return;
        }

        console.appendText("Card selected!\n");
        paymentSelected = true;
    }

    @FXML
    protected void onCheckClick() {

        if (paymentSelected) {
            console.appendText("Payment already selected.\n");
            return;
        }

        console.appendText("Check selected!\n");
        paymentSelected = true;
    }

    @FXML
    protected void onScanClick() {

        if(itemIndex >= itemList.size()){
            console.appendText("\tNo more items.\n");
            finishScanning = true;
            return;
        }

//        console.appendText("Scanned " + order.get_next_item_name());
        console.appendText("\tScanned " + itemList.get(itemIndex++).getName());
        console.appendText("\n");
    }

    @FXML
    protected void onNextOrderClick() {

        /*
        //Checking to see if orders array looks good
        for(Orders orders: Data.orders){
            System.out.println("\n****************************** ORDER #: ");
            System.out.println(orders.getId() +"\n");
            System.out.println(orders.getDate()+"\n");
            System.out.println(orders.getTime()+"\n");
            System.out.println(orders.getTotal() +"\n");
            System.out.println(orders.getTotal_Tax() +"\n");
            System.out.println(orders.getProcessed() +"\n");

            for(OrderItem items: orders.getItems()){
                System.out.println("\n\t"+ items.getId());
                System.out.println("\n\t"+items.getName());
                System.out.println("\n\t"+items.getDescription());
                System.out.println("\n\t"+items.getDiscount());
                System.out.println("\n\t"+ items.getWeight());
                System.out.println("\n\t"+ items.getPrice());
                System.out.println("\n\t"+ items.getQuantity());
            }

        }
        */


        //  /*
        if (!orderCompleted) {
            return;
        }
        orderCompleted = false;

        if (currentOrderIndex > Data.orders.size()) {
            console.appendText("No more customers in line.\n");
            return;
        }

        console.appendText("Order ID: " + Data.orders.get(currentOrderIndex).getId());
        console.appendText("\n");
        console.appendText("Order Date: " + Data.orders.get(currentOrderIndex).getDate());
        console.appendText("\n");
        console.appendText("Order Time: " + Data.orders.get(currentOrderIndex).getTime());
        console.appendText("\n");

        //  */

    }

    @FXML
    protected void onCompleteOrderClick() {

        if (itemIndex < itemList.size()) {
            console.appendText("Could not complete order. All items have not been processed.\n");
            return;
        }

        if (!paymentSelected) {
            console.appendText("Could not complete order. Order has not been payed for.\n");
            return;
        }

        console.appendText("Order Completed! Click on \"Get next order\" to get next order.\n\n");

        itemIndex = 0;
        orderCompleted = true;
        currentOrderIndex++;

        if(currentOrderIndex >= Data.orders.size()){
            console.appendText("\n\n[ ALL ORDERS PROCESSED ]");
        }else{
            itemList = Data.orders.get(currentOrderIndex).getItems();
        }

    }

    @FXML
    protected void onRequestPaymentClick() {

        if (!finishScanning) {
            console.appendText("All items have not been scanned!\n");
            return;
        }

        console.appendText("Waiting for customer to provide payment...\n");

        // TODO: randomize selection of payment methods.

        console.appendText("""
                Customer provided cash.
                \tAmount Provided: $20
                """);

    }


    //********************************************************
    @FXML
    protected void onCheckoutNextCustomerClick(){

        //Close Make Payment Stage/Window
        Stage window;
        window = (Stage) console.getScene().getWindow();
        window.close();
    }

    //********************************************************
    @FXML
    protected void onShutDownOKClick() throws IOException {


        Data.writeJSONfiles();
        //Close Application
        Platform.exit();
        System.exit(0);
    }

    //********************************************************

    @FXML
    protected void OKhover(){
        OKshutdownButton.setFont(new Font("Segoe UI Semibold",13));
    }
    @FXML
    protected void OKhoverExit(){
        OKshutdownButton.setFont(new Font("Segoe UI Semibold",11));
    }
    //********************************************************

    public void ShutDown_calcInventoryMessage(){

        inventoryPane.setVisible(true);

        //For Formatting Date
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();

        // Send/store inventory orders for product restock
        for(int i = 0; i < Data.items.size(); i++){

            // If product stock < threshold
            // Order enough quantity to bring stock to (threshold value + 50)
            if(Data.items.get(i).getQuantity() < Data.items.get(i).getThreshold() ){

                int quantity_toOrder = (Data.items.get(i).getThreshold()+50) -Data.items.get(i).getQuantity();
                //System.out.print(quantity_toOrder);
                String tmpMessage = "Item "+Data.items.get(i).getId()+" - "+Data.items.get(i).getName()+", is below threshold.";

                inventory_orders tmpOrder = new inventory_orders(
                        tmpMessage,
                        Date.format(now),
                        Data.items.get(i).getId(),
                        quantity_toOrder
                );

                Data.inventoryOrders.add(tmpOrder); //Adding to inventory orders in data object
                ManageStockLevel.addToItemStock(quantity_toOrder, i);       //Add items to products stock for index "i"

                //Add to text area of inventory messages pane
                inventoryMessages.appendText(
                        tmpMessage+"\n\t"+Date.format(now)+"\n\tQuantity ordering - "+String.valueOf(quantity_toOrder)+"\n\n"
                );
                System.out.println(tmpMessage);

            }
        }

    }
}