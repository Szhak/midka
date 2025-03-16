package coffee;

public class Latte implements Coffee {
    @Override
    public double getCost() {
        return 3.5;
    }

    @Override
    public String getDescription() {
        return "Latte";
    }
}
