// Sales.java
package com.example.coffee.cli;
import java.util.ArrayList;
import java.util.List;

public class Sales {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        if (!order.getItems().isEmpty()) {
            orders.add(order);
        }
    }

    public int countOrdersWithProduct(String code) {
        int count = 0;
        for (Order order : orders) {
            for (OrderItem item : order.getItems()) {
                if (item.getCode().equals(code)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
