// Order.java
package com.example.coffee.cli;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem newItem) {
        for (OrderItem item : items) {
            if (item.getCode().equals(newItem.getCode())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public void removeItem(String code) {
        items.removeIf(item -> item.getCode().equals(code));
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
