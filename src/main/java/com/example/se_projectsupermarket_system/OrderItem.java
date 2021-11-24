package com.example.se_projectsupermarket_system;

public class OrderItem {

    private int id;
    private String name;
    private String description;
    private String discount;
    private double weight;  //In Lbs
    private double price;
    private int quantity;

    public OrderItem(int item_id, String item_name, String item_description, String item_discount, double item_weight, double item_price, int item_quantity) {
        id = item_id;
        name = item_name;
        description = item_description;
        discount = item_discount;
        weight = item_weight;
        price = item_price;
        quantity = item_quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
}
