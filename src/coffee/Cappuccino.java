package coffee;

public class Cappuccino implements Coffee {
    @Override
    public double getCost() {
        return 3.0;
    }

    @Override
    public String getDescription() {
        return "Cappuccino";
    }
}
