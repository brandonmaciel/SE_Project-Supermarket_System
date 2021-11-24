package com.example.se_projectsupermarket_system;

public class Bank {

    private String name;
    private String card_no;
    private int cvv;
    private String exp_date;
    private String zip_code;
    private int pin;
    private double balance;

    public Bank(String name, String card_no, int cvv, String exp_date, String zip_code, int pin, double balance) {
        this.name = name;
        this.card_no = card_no;
        this.cvv = cvv;
        this.exp_date = exp_date;
        this.zip_code = zip_code;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
