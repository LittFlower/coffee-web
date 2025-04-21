// Accessory.java
package com.example.coffee.cli;
public class Accessory extends Product {
    public Accessory(String code, String description, double price) {
        super(code, description, price);
        if (!code.matches("A\\d{3}")) throw new IllegalArgumentException("Invalid Accessory Code");
    }
}
