package coffee;
public class Americano implements Coffee {
    @Override
    public double getCost() {
        return 2.5;
    }

    @Override
    public String getDescription() {
        return "Americano";
    }
}
