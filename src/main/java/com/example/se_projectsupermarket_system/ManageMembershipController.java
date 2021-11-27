package com.example.se_projectsupermarket_system;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class ManageMembershipController {

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











}
