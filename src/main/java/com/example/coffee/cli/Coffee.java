// Coffee.java - 咖啡产品
package com.example.coffee.cli;
public class Coffee extends Product {
    private String origin;
    private String roast;
    private String flavor;
    private String aroma;
    private String acidity;
    private String body;

    public Coffee(String code, String description, double price,
                  String origin, String roast, String flavor,
                  String aroma, String acidity, String body) {
        super(code, description, price);
        if (!code.matches("C\\d{3}")) throw new IllegalArgumentException("Invalid Coffee Code");
        this.origin = origin;
        this.roast = roast;
        this.flavor = flavor;
        this.aroma = aroma;
        this.acidity = acidity;
        this.body = body;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\nOrigin: %s | Roast: %s | Flavor: %s | Aroma: %s | Acidity: %s | Body: %s",
                origin, roast, flavor, aroma, acidity, body);
    }
}
