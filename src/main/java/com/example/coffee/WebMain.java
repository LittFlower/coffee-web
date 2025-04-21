package com.example.coffee;

import java.util.Map;
import com.example.coffee.cli.*;
import com.google.gson.Gson;
import static spark.Spark.*;

public class WebMain {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        Main.initializeSampleData();

        port(4567);
        staticFiles.location("/public");
        enableCORS();

        setupProductRoutes();
        setupOrderRoutes();
        setupSalesRoutes();
    }

    private static void enableCORS() {
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET,POST");
        });
    }

    private static void setupProductRoutes() {
        get("/api/products", (req, res) -> {
            res.type("application/json");
            return Main.catalog.getProducts().values();
        }, gson::toJson);

        get("/api/products/:code", (req, res) -> {
            Product p = Main.catalog.getProduct(req.params(":code"));
            return p != null ? p : "Product not found";
        }, gson::toJson);
    }

    private static void setupOrderRoutes() {
        get("/api/order", (req, res) -> Main.currentOrder.getItems(), gson::toJson);

        post("/api/order/items", (req, res) -> {
            Map<String, Object> data = gson.fromJson(req.body(), Map.class);
            String code = (String) data.get("code");
            int quantity = ((Double) data.get("quantity")).intValue();

            Product product = Main.catalog.getProduct(code);
            if (product == null) return halt(404, "Product not found");

            Main.currentOrder.addItem(new OrderItem(
                product.getCode(),
                product.getDescription(),
                product.getPrice(),
                quantity
            ));
            return "Added " + quantity + "x " + code;
        });

        delete("/api/order/items/:code", (req, res) -> {
            Main.currentOrder.removeItem(req.params(":code"));
            return "Removed";
        });
    }

    private static void setupSalesRoutes() {
        post("/api/sales", (req, res) -> {
            if (Main.currentOrder.getItems().isEmpty()) {
                return halt(400, "Empty order");
            }
            Main.sales.addOrder(Main.currentOrder);
            Main.currentOrder = new Order();
            return "Sale registered";
        });

        get("/api/sales", (req, res) -> Main.sales.getOrders(), gson::toJson);
    }
}
