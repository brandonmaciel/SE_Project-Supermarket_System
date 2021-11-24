package com.example.se_projectsupermarket_system;

public class Orders {

    private String date;
    private int id;
    private double total;
    private Items items;

    public Orders(String order_date, int order_id, double order_total, Items order_items) {
        date = order_date;
        id = order_id;
        total = order_total;
        items = order_items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
