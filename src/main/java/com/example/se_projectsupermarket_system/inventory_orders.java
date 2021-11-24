package com.example.se_projectsupermarket_system;

public class inventory_orders {

    private String message;
    private String date;
    private int id;
    private int quantity;

    public inventory_orders(String inventory_message, String order_date, int item_id,
                            int item_quantity){
        message = inventory_message;
        date = order_date;
        id = item_id;
        quantity = item_quantity;
    }

    public String getMessage(){ return message; }

    public void setMessage(String message){ this.message = message; }

    public String getDate(){ return date; }

    public void setDate(String date){ this.date = date; }

    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

    public int getQuantity(){ return quantity; }

    public void setQuantity(int quantity){ this.quantity = quantity; }
}
