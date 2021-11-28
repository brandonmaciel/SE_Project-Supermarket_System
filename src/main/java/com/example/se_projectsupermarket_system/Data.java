package com.example.se_projectsupermarket_system;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Data {

    final static String bankPath = "src/main/resources/data/bank.json";
    final static String checkoutPath = "src/main/resources/data/checkout_orders_records.json";
    final static String inventoryPath = "src/main/resources/data/inventory_orders_records.json";
    final static String membershipPath = "src/main/resources/data/membership_account.json";
    final static String productPath = "src/main/resources/data/product_inventory.json";

    static List<Bank> bank = new ArrayList<>();
    static List<Orders> orders = new ArrayList<>();
    static List<inventory_orders> inventoryOrders = new ArrayList<>();
    static List<MembersAccount> members = new ArrayList<>();
    static List<Item> items = new ArrayList<>();

    static int currentOrderIndex;

    //***************************************************************************************
    public static void parseFiles() throws IOException {
        parseBankJSON();
        parseCheckoutOrders();
        parseInventoryOrdersJSON();
        parseMembershipAccounts();
        parseProductInventory();


        for(int index = 0; index < orders.size(); index++) {
            if(orders.get(index).getProcessed()) {
                currentOrderIndex = index;
                break;
            }
        }
    }

    //***************************************************************************************
    public static void parseBankJSON() throws IOException {

        String jsonString = Files.readString(Path.of(bankPath), StandardCharsets.US_ASCII);
        JSONArray bankArray = new JSONArray(jsonString);

        for(int i = 0; i < bankArray.length(); i++){

            JSONObject tmpObj = bankArray.getJSONObject(i);

            Bank tmpBank = new Bank(
                    tmpObj.getString("name"),
                    tmpObj.getString("card_no"),
                    tmpObj.getInt("cvv"),
                    tmpObj.getString("exp_date"),
                    tmpObj.getString("zip_code"),
                    tmpObj.getInt("pin"),
                    tmpObj.getDouble("balance"));

            bank.add(tmpBank);
        }
    }

    //***************************************************************************************
    public static void parseCheckoutOrders() throws IOException {

        OrderItem tmpItem;

        String jsonString = Files.readString(Path.of(Data.checkoutPath), StandardCharsets.US_ASCII);
        JSONArray ordersArray = new JSONArray(jsonString);

        for(int i = 0; i < ordersArray.length(); i++) {
            List<OrderItem> orderItems = new ArrayList<>();

            JSONObject tmpOrderObj = ordersArray.getJSONObject(i);
            JSONArray itemsArray = new JSONArray(tmpOrderObj.getJSONArray("items"));

            for(int j = 0; j < itemsArray.length(); j++) {
                JSONObject tmpItemObj = itemsArray.getJSONObject(j);
                tmpItem = new OrderItem(
                        tmpItemObj.getInt("item_id"),
                        tmpItemObj.getString("item_name"),
                        tmpItemObj.getString("item_description"),
                        tmpItemObj.getString("discount"),
                        tmpItemObj.getDouble("item_weight"),
                        tmpItemObj.getDouble("item_price"),
                        tmpItemObj.getInt("quantity"));

                orderItems.add(tmpItem);
            }

            Orders tmpOrder = new Orders(
                    tmpOrderObj.getInt("order_id"),
                    tmpOrderObj.getString("order_date"),
                    tmpOrderObj.getString("order_time"),
                    tmpOrderObj.getDouble("total"),
                    tmpOrderObj.getDouble("total_tax"),
                    tmpOrderObj.getBoolean("processed"),
                    orderItems);

            orders.add(tmpOrder);
        }
    }

    //***************************************************************************************
    public static void parseInventoryOrdersJSON() throws IOException {

        String jsonString = Files.readString(Path.of(inventoryPath), StandardCharsets.US_ASCII);
        JSONArray inventoryOrdersArray = new JSONArray(jsonString);

        for(int i = 0; i < inventoryOrdersArray.length(); i++){

            JSONObject tmpObj = inventoryOrdersArray.getJSONObject(i);

            inventory_orders tmpOrder = new inventory_orders(
                    tmpObj.getString("message"),
                    tmpObj.getString("order_date"),
                    tmpObj.getInt("item_id"),
                    tmpObj.getInt("quantity_ordered")
            );


            inventoryOrders.add(tmpOrder);
        }
    }

    //***************************************************************************************
    public static void parseMembershipAccounts() throws IOException{

        String jsonString = Files.readString(Path.of(Data.membershipPath), StandardCharsets.US_ASCII);
        JSONArray membersArray = new JSONArray(jsonString);

        for(int i = 0; i < membersArray.length(); i++){
            List<Integer> ordersID = new ArrayList<>();

            JSONObject tmpMemberObj = membersArray.getJSONObject(i);
            JSONArray ordersIDArray = new JSONArray(tmpMemberObj.getJSONArray("orders_id"));

            //Add orders ID to List<integer> OrdersID
            for(int j = 0; j < ordersIDArray.length(); j++){
                int tmpOrderID;
                tmpOrderID = ordersIDArray.getInt(j);

                ordersID.add(tmpOrderID);
            }

            // Create tmpMember and add to List<MembersAccount> members of Data.members
            MembersAccount tmpMember = new MembersAccount(
                    tmpMemberObj.getString("member_name"),
                    tmpMemberObj.getString("phone_num"),
                    tmpMemberObj.getInt("member_pin"),
                    tmpMemberObj.getInt("credit_points"),
                    ordersID);

            members.add(tmpMember);
        }
    }

    //***************************************************************************************
    public static void parseProductInventory() throws IOException {

        String jsonString = Files.readString(Path.of(productPath), StandardCharsets.US_ASCII);
        JSONArray productsArray = new JSONArray(jsonString);

        for(int i = 0; i < productsArray.length(); i++){

            JSONObject tmpObj = productsArray.getJSONObject(i);

            Item tmpItem = new Item(
                    tmpObj.getInt("item_id"),
                    tmpObj.getString("item_name"),
                    tmpObj.getString("item_description"),
                    tmpObj.getString("discount"),
                    tmpObj.getDouble("item_price"),
                    tmpObj.getInt("quantity"),
                    tmpObj.getInt("threshold"),
                    tmpObj.getBoolean("bulk_boolean"),
                    tmpObj.getDouble("item_weight"));

            items.add(tmpItem);
        }
    }



}