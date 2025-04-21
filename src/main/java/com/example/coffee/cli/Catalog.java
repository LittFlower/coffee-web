// Catalog.java
package com.example.coffee.cli;
import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getCode(), product);
    }

    public Product getProduct(String code) {
        return products.get(code);
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}
