// OrderItem.java
package com.example.coffee.cli;
public class OrderItem {
    private String code;
    private String description;
    private double price;
    private int quantity;

    public OrderItem(String code, String description, double price, int quantity) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() { return quantity * price; }

    public String getCode(){
        return code;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
