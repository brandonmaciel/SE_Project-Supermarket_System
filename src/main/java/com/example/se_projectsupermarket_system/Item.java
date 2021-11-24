package com.example.se_projectsupermarket_system;

public class Item {

    private int id;
    private String name;
    private String description;
    private String discount;
    private double price;
    private int quantity;
    private int threshold;
    private boolean bulk;
    private double weight;  //In Lbs


    public Item(int item_id, String item_name, String item_description, String item_discount, double item_price,
                int item_quantity, int item_threshold, boolean item_bulk, double item_weight){
        id = item_id;
        name = item_name;
        description = item_description;
        discount = item_discount;
        price = item_price;
        quantity = item_quantity;
        threshold = item_threshold;
        bulk = item_bulk;
        weight = item_weight;
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

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public boolean getBulk() {
        return bulk;
    }

    public void setBulk(boolean bulk) {
        this.bulk = bulk;
    }

    public double getWeight() {
        return weight;
    }

    public void seWeight(double weight) {
        this.weight = weight;
    }

}
