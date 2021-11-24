package com.example.se_projectsupermarket_system;

public class MembersAccount {

    private String name;
    private String phone_num;
    private int pin;
    private int credit_points;
    private checkout_orders orders; //Orders as list of object Orders or list of order_ID ints,
                            // I think it's better to have list of Objects???

    public MembersAccount(String customer_name, String customer_phone_num, int customer_pin,
                          int customer_points, checkout_orders customer_orders) {
        name = customer_name;
        phone_num = customer_phone_num;
        pin = customer_pin;
        credit_points = customer_points;
        orders = customer_orders;
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

    public checkout_orders getOrders() {
        return orders;
    }

    public void setOrders(checkout_orders orders) {
        this.orders = orders;
    }
}
