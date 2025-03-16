package coffee;

// Шоколад
public class ChocolateDecorator extends CoffeeDecorator {
    public ChocolateDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.8;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Chocolate";
    }
}
