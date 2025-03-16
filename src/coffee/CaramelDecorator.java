package coffee;

// Карамель
public class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.7;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Caramel";
    }
}
