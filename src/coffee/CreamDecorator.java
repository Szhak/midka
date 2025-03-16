package coffee;

// Сливки
public class CreamDecorator extends CoffeeDecorator {
    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.6;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Cream";
    }
}
