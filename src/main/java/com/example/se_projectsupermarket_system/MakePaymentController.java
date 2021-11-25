package com.example.se_projectsupermarket_system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.List;

public class MakePaymentController {

    private boolean paymentSelected = false;
    private boolean finishScanning = false;
    private boolean orderCompleted = true;
    private int currentOrderIndex = Data.currentOrderIndex;
    private List<OrderItem> itemList = Data.orders.get(currentOrderIndex).getItems();
    private int itemIndex = 0;


    @FXML
    private TextArea console;

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
        itemList = Data.orders.get(currentOrderIndex).getItems();
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

}