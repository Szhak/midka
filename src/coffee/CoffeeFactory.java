package coffee;

public class CoffeeFactory {
    public static Coffee createCoffee(String type) {
        return switch (type.toLowerCase()) {
            case "espresso" -> new Espresso();
            case "cappuccino" -> new Cappuccino();
            case "latte" -> new Latte();
            case "americano" -> new Americano();
            default -> throw new IllegalArgumentException("Unknown coffee type: " + type);
        };
    }
}
