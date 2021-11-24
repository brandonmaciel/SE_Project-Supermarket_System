package com.example.se_projectsupermarket_system;

public class Items {

    private String name;
    private int id;
    private double price;
    private int quantity;
    private String description;

    public Items(String item_name, int item_id, double item_price, int item_quantity, String item_description){
        name = item_name;
        id = item_id;
        price = item_price;
        quantity = item_quantity;
        description = item_description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
