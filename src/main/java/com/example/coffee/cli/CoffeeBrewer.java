// CoffeeBrewer.java - 冲泡机产品
package com.example.coffee.cli;

public class CoffeeBrewer extends Product {
    private String model;
    private String waterSupply;
    private int capacity;

    public CoffeeBrewer(String code, String description, double price,
                        String model, String waterSupply, int capacity) {
        super(code, description, price);
        if (!code.matches("B\\d{3}")) throw new IllegalArgumentException("Invalid Brewer Code");
        if (!waterSupply.matches("Pour-over|Automatic"))
            throw new IllegalArgumentException("Invalid Water Supply");
        this.model = model;
        this.waterSupply = waterSupply;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\nModel: %s | Water Supply: %s | Capacity: %d",
                model, waterSupply, capacity);
    }
}
