package com.example.se_projectsupermarket_system;

public class checkout_orders {

    private int id;
    private String date;
    private String time;
    private double total;
    private double total_tax;
    private Items items; //Bulk & threshold fields will be empty.

    public checkout_orders(int order_id, String order_date, String order_time,
                           double order_total, double order_total_tax, Items order_items) {
        id = order_id;
        date = order_date;
        time = order_time;
        total = order_total;
        total_tax = order_total_tax;
        items = order_items;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal_Tax() {
        return total_tax;
    }

    public void setTotal_Tax(double total_tax) {
        this.total_tax = total_tax;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
