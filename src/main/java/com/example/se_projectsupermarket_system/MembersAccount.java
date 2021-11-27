package com.example.se_projectsupermarket_system;

import java.util.List;

public class MembersAccount {

    private String name;
    private String phone_num;
    private int pin;
    private int credit_points;
    private List<Integer> orders_id; //List of member's orders' ID, parse through Data.orders


    public MembersAccount(String customer_name, String customer_phone_num, int customer_pin,
                          int customer_points, List<Integer> customer_orders_id) {
        name = customer_name;
        phone_num = customer_phone_num;
        pin = customer_pin;
        credit_points = customer_points;
        orders_id = customer_orders_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCredit_points(){
        return credit_points;
    }

    public void setCredit_points(int credit_points){
        this.credit_points = credit_points;
    }

    public List<Integer> getOrders() {
        return orders_id;
    }

    public void setOrders(List<Integer> orders_id) {
        this.orders_id = orders_id;
    }
}
