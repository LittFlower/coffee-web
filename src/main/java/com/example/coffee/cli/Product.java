// Product.java
package com.example.coffee.cli;
public abstract class Product {
    private String code;
    private String description;
    private double price;

    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    // Getter方法
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("Code: %s | Description: %s | Price: %.2f",
                code, description, price);
    }
}
