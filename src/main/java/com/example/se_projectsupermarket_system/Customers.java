package com.example.se_projectsupermarket_system;

public class Customers {

    private String name;
    private String phone_no;
    private int pin;
    private Orders orders;

    public Customers(String customer_name, String customer_phone_no, int customer_pin, Orders customer_orders) {
        name = customer_name;
        phone_no = customer_phone_no;
        pin = customer_pin;
        orders = customer_orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
