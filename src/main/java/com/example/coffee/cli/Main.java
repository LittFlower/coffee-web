// Main.java - Complete implementation
package com.example.coffee.cli;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.InputMismatchException;


public class Main {
    public static Catalog catalog = new Catalog();
    public static Order currentOrder = new Order();
    public static Sales sales = new Sales();
    private static Scanner scanner = new Scanner(System.in);

    // public static void main(String[] args) {
    //     initializeSampleData();
    //     while (true) {
    //         try {
    //             printMenu();
    //             int choice = scanner.nextInt();
    //             scanner.nextLine();
    //
    //             switch (choice) {
    //                 case 0 -> System.exit(0);
    //                 case 1 -> displayCatalog();
    //                 case 2 -> displayProduct();
    //                 case 3 -> displayCurrentOrder();
    //                 case 4 -> addToOrder();
    //                 case 5 -> removeFromOrder();
    //                 case 6 -> registerSale();
    //                 case 7 -> displaySales();
    //                 case 8 -> displayOrdersWithProduct();
    //                 case 9 -> displayTotalQuantitySold();
    //                 default -> System.out.println("Invalid choice!");
    //             }
    //         } catch (InputMismatchException e) {
    //             System.out.println("Invalid choice!");
    //             scanner.nextLine();
    //         } catch (Exception e) {
    //             System.out.println("Unexpected error: " + e.getMessage());
    //             scanner.nextLine();
    //         }
    //     }
    // }

    public static void printMenu() {
        System.out.println("\n[0] Quit");
        System.out.println("[1] Display catalog");
        System.out.println("[2] Display product");
        System.out.println("[3] Display current order");
        System.out.println("[4] Add product to order");
        System.out.println("[5] Remove product from current order");
        System.out.println("[6] Register sale of current order");
        System.out.println("[7] Display sales");
        System.out.println("[8] Display number of orders with a specific product");
        System.out.println("[9] Display the total quantity sold for each product");
        System.out.print("choice> ");
    }

    public static void initializeSampleData() {

        catalog.addProduct(new Accessory("A001", "Almond Flavored Syrup", 9.00));
        catalog.addProduct(new Accessory("A002", "Irish Creme Flavored Syrup", 9.00));
        catalog.addProduct(new Accessory("A005", "Gourmet Coffee Cookies", 12.00));
        catalog.addProduct(new Accessory("A007", "Gourmet Coffee Ceramic Mug", 8.00));
        catalog.addProduct(new Accessory("A009", "Gourmet Coffee 36 Cup Filters", 45.00));

        catalog.addProduct(new CoffeeBrewer("B001", "Home Coffee Brewer", 150.00,
                "Brewer 100", "Pour-over", 6));
        catalog.addProduct(new CoffeeBrewer("B002", "Coffee Brewer, 2 Warmers", 200.00,
                "Brewer 200", "Pour-over", 12));
        catalog.addProduct(new CoffeeBrewer("B003", "Coffee Brewer, 3 Warmers", 280.00,
                "Brewer 210", "Pour-over", 12));
        catalog.addProduct(new CoffeeBrewer("B004", "Commercial Brewer, 20 cups", 380.00,
                "Quick Coffee 100", "Automatic", 20));
        catalog.addProduct(new CoffeeBrewer("B005", "Commercial Brewer, 40 cups", 480.00,
                "Quick Coffee 200", "Automatic", 40));

        catalog.addProduct(new Coffee("C001", "Colombia, Whole, 1 lb", 17.99,
                "Colombia", "Medium", "Rich and Hearty", "Rich", "Medium", "Full"));
        catalog.addProduct(new Coffee("C002", "Colombia, Ground, 1 lb", 18.75,
                "Colombia", "Medium", "Rich and Hearty", "Rich", "Medium", "Full"));
        catalog.addProduct(new Coffee("C007", "Guatemala, Whole, 1 lb", 17.99,
                "Guatemala", "Medium", "Rich and complex", "Spicy", "Medium to high", "Medium to full"));
        catalog.addProduct(new Coffee("C008", "Guatemala, Ground, 1 lb", 18.75,
                "Guatemala", "Medium", "Rich and complex", "Spicy", "Medium to high", "Medium to full"));
    }

    public static void displayCatalog() {
        System.out.println("\nPRODUCT CATALOG:");
        catalog.getProducts().values().forEach(p ->
                System.out.println(p.getCode() + " - " + p.getDescription()));
    }

    private static void displayProduct() {
        System.out.print("Enter product code: ");
        Product p = catalog.getProduct(scanner.nextLine());
        if (p != null) {
            System.out.println("\nPRODUCT DETAILS:\n" + p);
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void displayCurrentOrder() {
        System.out.println("\nCURRENT ORDER:");
        if (currentOrder.getItems().isEmpty()) {
            System.out.println("Order is empty");
            return;
        }

        currentOrder.getItems().forEach(item ->
                System.out.printf("%dx %s - $%.2f each\n",
                        item.getQuantity(), item.getCode(), item.getPrice()));
        System.out.printf("TOTAL: $%.2f\n", currentOrder.getTotal());
    }

    private static void addToOrder() {
        System.out.print("Enter product code: ");
        String code = scanner.nextLine();
        Product product = catalog.getProduct(code);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        currentOrder.addItem(new OrderItem(
                product.getCode(),
                product.getDescription(),
                product.getPrice(),
                quantity
        ));
        System.out.println("Product added to order");
    }

    private static void removeFromOrder() {
        System.out.print("Enter product code to remove: ");
        String code = scanner.nextLine();
        currentOrder.removeItem(code);
        System.out.println("Product removed if present");
    }

    private static void registerSale() {
        if (currentOrder.getItems().isEmpty()) {
            System.out.println("Current order is empty!");
            return;
        }
        sales.addOrder(currentOrder);
        currentOrder = new Order();
        System.out.println("Order registered successfully");
    }

    private static void displaySales() {
        System.out.println("\nSALES HISTORY:");
        if (sales.getOrders().isEmpty()) {
            System.out.println("No sales recorded");
            return;
        }

        for (int i = 0; i < sales.getOrders().size(); i++) {
            Order order = sales.getOrders().get(i);
            System.out.printf("Order %d:\n", i+1);
            order.getItems().forEach(item ->
                    System.out.printf(" - %dx %s ($%.2f)\n",
                            item.getQuantity(), item.getCode(), item.getPrice()));
            System.out.printf("Total: $%.2f\n\n", order.getTotal());
        }
    }

    private static void displayOrdersWithProduct() {
        System.out.print("Enter product code: ");
        String code = scanner.nextLine();
        int count = sales.countOrdersWithProduct(code);
        System.out.printf("Number of orders containing %s: %d\n", code, count);
    }

    private static void displayTotalQuantitySold() {
        Map<String, Integer> quantities = new HashMap<>();

        // Aggregate quantities from all sales
        sales.getOrders().forEach(order ->
                order.getItems().forEach(item ->
                        quantities.merge(item.getCode(), item.getQuantity(), Integer::sum)));

        System.out.println("\nTOTAL QUANTITIES SOLD:");
        if (quantities.isEmpty()) {
            System.out.println("No sales recorded");
            return;
        }

        quantities.forEach((code, qty) ->
                System.out.printf("%s: %d units\n", code, qty));
    }
}
