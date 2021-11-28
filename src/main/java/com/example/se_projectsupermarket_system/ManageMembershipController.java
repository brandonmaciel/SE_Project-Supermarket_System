package com.example.se_projectsupermarket_system;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class ManageMembershipController {


    //Current Member's Login Info
    static String member_phoneNum;
    static int member_pin;




    // Our Four Manage Account Panes/scenarios
    @FXML
    private Pane pane_VerifyAccount;
    @FXML
    private Pane pane_VerifyDone;
    @FXML
    private Pane pane_CreateAccount;
    @FXML
    private Pane pane_CreateDone;


    // Verify Account Pane stuff - VA
    @FXML
    private TextField VA_enteredPhoneNum;
    @FXML
    private PasswordField VA_enteredPin;
    @FXML
    private Button VA_verify;
    @FXML
    private Button VA_createAccount;
    @FXML
    private Button VA_cancel;
    @FXML
    private Label VA_invalidAccount;

    // Verify Done Pane stuff - VD
    @FXML
    private Label VD_memberName;
    @FXML
    private Label VD_phoneNum;
    @FXML
    private Label VD_pin;
    @FXML
    private Label VD_creditPoints;
    @FXML
    private Button VD_closeWindow;

    // Create Account Pane stuff - CA
    @FXML
    private TextField CA_enteredName;
    @FXML
    private TextField CA_enteredPhoneNum;
    @FXML
    private PasswordField CA_enteredPin;
    @FXML
    private Button CA_create;

    // Create Done Pane stuff - CD
    @FXML
    private Label CD_memberName;
    @FXML
    private Label CD_phoneNum;
    @FXML
    private Label CD_pin;
    @FXML
    private Label CD_creditPoints;
    @FXML
    private Button CD_closeWindow;



    //***************************************************************************************
    @FXML
    protected void onVerify_click(){

        member_phoneNum = VA_enteredPhoneNum.getText();
        member_pin = Integer.valueOf(VA_enteredPin.getText());

        //Check to see if valid in List of Members in Data
        for(MembersAccount member: Data.members){
            if(member.getPin() == member_pin && member.getPhone_num().contains(member_phoneNum)){


                //If account existed than change pane and display account info
                VA_invalidAccount.setVisible(false);
                pane_VerifyAccount.setVisible(false);
                pane_VerifyDone.setVisible(true);

                //Display member's data onto Account Verified Pane
                VD_memberName.setText(member.getName());
                VD_phoneNum.setText(member.getPhone_num());
                VD_pin.setText(String.valueOf(member.getPin()));
                VD_creditPoints.setText(String.valueOf(member.getCredit_points()));
            }else {
                VA_invalidAccount.setVisible(true);
            }
        }
    }

    //***************************************************************************************
    @FXML
    protected void onCancel_click(){

        //Setting to a non-valid account
        member_phoneNum = "";
        member_pin = 0;

        //Close Manage Account Stage/Window
        Stage window;
        window = (Stage) VA_cancel.getScene().getWindow();
        window.close();
    }

    //***************************************************************************************
    @FXML
    protected void onCreateAccount_click(){

        //Change to CreateAccount Pane
        pane_VerifyAccount.setVisible(false);
        pane_CreateAccount.setVisible(true);
    }

    //***************************************************************************************
    @FXML
    protected void onCreate_click(){


        //Make temporary MembersAccount object to add to Data.members List
        List<Integer> ordersID = new ArrayList<>();

        MembersAccount tmpMember = new MembersAccount(
                CA_enteredName.getText(),
                CA_enteredPhoneNum.getText(),
                Integer.valueOf(CA_enteredPin.getText()),
                0,
                ordersID
        );

        Data.members.add(tmpMember);

        //Change to Account created Pane
        pane_CreateAccount.setVisible(false);
        pane_CreateDone.setVisible(true);

        //Set CreateDone Pane values
        CD_memberName.setText(tmpMember.getName());
        CD_phoneNum.setText(tmpMember.getPhone_num());
        CD_pin.setText(String.valueOf(tmpMember.getPin()));
        CD_creditPoints.setText(String.valueOf(tmpMember.getCredit_points()));

        //Set/Save current newly created Member's login info
        member_pin = tmpMember.getPin();
        member_phoneNum = tmpMember.getPhone_num();


    }

    //***************************************************************************************
    @FXML
    protected void onCloseWindow_click(){

        //Close Manage Account Stage/Window
        Stage window;
        window = (Stage) VA_cancel.getScene().getWindow();
        window.close();

    }

    //***************************************************************************************

    public static void addCreditPoints() {

        //Check to see if Account is valid in List of Members in Data
        for (int i = 0; i < Data.members.size(); i++) {
            if (Data.members.get(i).getPin() == member_pin && Data.members.get(i).getPhone_num().contains(member_phoneNum)) {

                //If account is valid, add credit points to account based on Order's Total Price
                int new_creditPoints = ((int)(CheckoutOrderController.OrderTotal * 0.10) + Data.members.get(i).getCredit_points() );
                Data.members.get(i).setCredit_points(new_creditPoints);
                //System.out.println(new_creditPoints);
            }
        }
    }

    //***************************************************************************************
    public static void addOrderID(int currentOrderId) {
        //Add Current Order ID to Data.members; current Member's orders_id integer array;
        // if applicable

        //Check to see if Account is valid in List of Members in Data
        for (int i = 0; i < Data.members.size(); i++) {
            if (Data.members.get(i).getPin() == member_pin && Data.members.get(i).getPhone_num().contains(member_phoneNum)) {

                //If account is valid, add Current Order ID to their orders_id integer array
                Data.members.get(i).getOrders().add(currentOrderId);
                System.out.println(Data.members.get(i).getOrders());
            }
        }
    }





    //***************************************************************************************
    @FXML
    protected void cancelHover(){
        VA_cancel.setFont(new Font("Segoe UI Semibold",14));
    }
    @FXML
    protected void cancelHoverExit(){
        VA_cancel.setFont(new Font("Segoe UI Light",12));
    }

    //***************************************************************************************
    @FXML
    protected void verifyHover(){
        VA_verify.setFont(new Font("Segoe UI Semibold",27));
    }
    @FXML
    protected void verifyExit(){
        VA_verify.setFont(new Font("Segoe UI Semibold",23));
    }

    //***************************************************************************************
    @FXML
    protected void createAccountHover(){
        VA_createAccount.setFont(new Font("Segoe UI Semibold",14));
    }
    @FXML
    protected void createAccountExit(){
        VA_createAccount.setFont(new Font("Segoe UI Light",12));
    }

    //***************************************************************************************
    @FXML
    protected void closeWindowHover(){
        VD_closeWindow.setFont(new Font("Segoe UI Semibold",27));
        CD_closeWindow.setFont(new Font("Segoe UI Semibold",27));
    }
    @FXML
    protected void closeWindowExit(){
        VD_closeWindow.setFont(new Font("Segoe UI Semibold",23));
        CD_closeWindow.setFont(new Font("Segoe UI Semibold",23));
    }

    //***************************************************************************************
    @FXML
    protected void createHover(){
        CA_create.setFont(new Font("Segoe UI Semibold",27));
    }
    @FXML
    protected void createExit(){
        CA_create.setFont(new Font("Segoe UI Semibold",23));
    }


}
