package com.example.se_projectsupermarket_system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MakePaymentController {

    private boolean paymentSelected = false;

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

//        if(order.current_item == null){
//            console.appendText("No more items.\n");
//        }

//        console.appendText("Scanned " + order.get_next_item_name());
        console.appendText("Scanned ITEM_NAME_HERE\n");
    }

    @FXML
    protected void onNextOrderClick() {

//        if (!customer.isCustomer) {
//            console.appendText("No more customers in line.\n");
//            return;
//        }

        console.appendText("Customer: CUSTOMER_NAME_HERE\n");
    }

}