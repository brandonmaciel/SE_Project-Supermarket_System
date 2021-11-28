package com.example.se_projectsupermarket_system;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
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




    //****************************************************************************
    //****************************************************************************
    public static void writeJSONfiles() throws IOException{
        writeBankJSON();
        // writeCheckoutOrders();
        // writeInventoryOrdersJSON();
        // writeMembershipAccounts();
        // writeProductInventory();  //DONE
    }

    //****************************************************************************
    public static void writeBankJSON() throws IOException{

    }

    //****************************************************************************
    public static void writeCheckoutOrders() throws IOException{

        JSONArray checkoutArray = new JSONArray();
        //For every order Data.orders; add it to checkoutArray using tmp JSON object
        for(int i = 0; i < Data.orders.size(); i++){

            //For writing correct key string value of orders array in items
            JSONArray orderItemsArray = new JSONArray();
            for(int j = 0; j < Data.orders.get(i).getItems().size(); j++){
                JSONObject tmpOrderItem = new JSONObject();
                tmpOrderItem.put("item_id",  Data.orders.get(i).getItems().get(j).getId() );
                tmpOrderItem.put("item_name", Data.orders.get(i).getItems().get(j).getName() );
                tmpOrderItem.put("item_description", Data.orders.get(i).getItems().get(j).getDescription() );
                tmpOrderItem.put("discount", Data.orders.get(i).getItems().get(j).getDiscount() );
                tmpOrderItem.put("item_weight", Data.orders.get(i).getItems().get(j).getWeight() );
                tmpOrderItem.put("item_price", Data.orders.get(i).getItems().get(j).getPrice() );
                tmpOrderItem.put("quantity", Data.orders.get(i).getItems().get(j).getQuantity() );

                orderItemsArray.put(tmpOrderItem);
            }


            JSONObject tmpOrder = new JSONObject();
            tmpOrder.put("order_id", Data.orders.get(i).getId() );
            tmpOrder.put("order_date", Data.orders.get(i).getDate() );
            tmpOrder.put("order_time", Data.orders.get(i).getTime() );
            tmpOrder.put("total", Data.orders.get(i).getTotal() );
            tmpOrder.put("total_tax", Data.orders.get(i).getTotal_Tax() );
            tmpOrder.put("processed", Data.orders.get(i).getProcessed() );
            tmpOrder.put("items",  orderItemsArray);


            checkoutArray.put(tmpOrder);
        }


        try{
            FileWriter file = new FileWriter("src/main/resources/data/newCheckout_orders.json");
            file.write('[');
            file.write('\n'); file.write(' ');
            for(int i = 0; i < checkoutArray.length(); i++){

                file.write(checkoutArray.getJSONObject(i).toString(2));
                if( (i+1) < checkoutArray.length()){
                    file.write(',');
                }
                file.write('\n');
            }
            file.write(']');
            file.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //****************************************************************************
    public static void writeInventoryOrdersJSON() throws IOException{

        //OverWrite inventory_orders JSON file
        JSONArray inventoryMessagesArray = new JSONArray();
        //For every inventory message Data.inventoryOrders; tmp JSON object
        for(int i = 0; i < Data.inventoryOrders.size(); i++){

            JSONObject tmpMessage = new JSONObject();
            tmpMessage.put("message", Data.inventoryOrders.get(i).getMessage() );
            tmpMessage.put("order_date", Data.inventoryOrders.get(i).getDate() );
            tmpMessage.put("item_id", Data.inventoryOrders.get(i).getId() );
            tmpMessage.put("quantity_ordered", Data.inventoryOrders.get(i).getQuantity() );

            inventoryMessagesArray.put(tmpMessage);
        }


        try{
            FileWriter file = new FileWriter("src/main/resources/data/new_messages.json");
            file.write('[');
            file.write('\n'); file.write(' ');
            for(int i = 0; i < inventoryMessagesArray.length(); i++){

                file.write(inventoryMessagesArray.getJSONObject(i).toString(2));
                if( (i+1) < inventoryMessagesArray.length()){
                    file.write(',');
                }
                file.write('\n');
            }
            file.write(']');
            file.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //****************************************************************************
    public static void writeMembershipAccounts() throws IOException{

        //OverWrite membership_account JSON file
        JSONArray membersArray = new JSONArray();
        //For every member Data.members; tmp JSON object
        for(int i = 0; i < Data.members.size(); i++){

            JSONObject tmpMember = new JSONObject();
            tmpMember.put("member_name", Data.members.get(i).getName() );
            tmpMember.put("phone_num", Data.members.get(i).getPhone_num() );
            tmpMember.put("member_pin", Data.members.get(i).getPin() );
            tmpMember.put("credit_points", Data.members.get(i).getCredit_points() );
            tmpMember.put("orders_id", Data.members.get(i).getOrders() );


            membersArray.put(tmpMember);
        }


        try{
            FileWriter file = new FileWriter("src/main/resources/data/new_accounts.json");
            file.write('[');
            file.write('\n'); file.write(' ');
            for(int i = 0; i < membersArray.length(); i++){

                file.write(membersArray.getJSONObject(i).toString(2));
                if( (i+1) < membersArray.length()){
                    file.write(',');
                }
                file.write('\n');
            }
            file.write(']');
            file.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //****************************************************************************
    public static void writeProductInventory() throws IOException{

        //OverWrite Product Inventory JSON file
        JSONArray productsArray = new JSONArray();
        //For every item Data.items; add it to productsArray using tmp JSON object
        for(int i = 0; i < Data.items.size(); i++){

            JSONObject tmpProduct = new JSONObject();
            tmpProduct.put("item_id", Data.items.get(i).getId());
            tmpProduct.put("item_name", Data.items.get(i).getName());
            tmpProduct.put("item_description", Data.items.get(i).getDescription());
            tmpProduct.put("discount", Data.items.get(i).getDiscount());
            tmpProduct.put("item_price", Data.items.get(i).getPrice());
            tmpProduct.put("quantity", Data.items.get(i).getQuantity());
            tmpProduct.put("threshold", Data.items.get(i).getThreshold());
            tmpProduct.put("bulk_boolean", Data.items.get(i).getBulk());
            tmpProduct.put("item_weight", Data.items.get(i).getWeight());

            productsArray.put(tmpProduct);
        }


        try{
            FileWriter file = new FileWriter(productPath);
            file.write('[');
            file.write('\n'); file.write(' ');
            for(int i = 0; i < productsArray.length(); i++){

                file.write(productsArray.getJSONObject(i).toString(2));
                if( (i+1) < productsArray.length()){
                    file.write(',');
                }
                file.write('\n');
            }
            file.write(']');
            file.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }



}