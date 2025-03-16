package coffee;

import java.util.Scanner;


public class CoffeeShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Coffee Shop!");
        System.out.println("Available drinks: Espresso, Cappuccino, Latte, Americano");
        System.out.print("Choose your coffee: ");
        String choice = scanner.nextLine();

        Coffee coffee;
        try {
            coffee = CoffeeFactory.createCoffee(choice);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Добавляем ингредиенты
        System.out.println("Would you like to add ingredients? (y/n)");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("Available ingredients: Milk, Caramel, Cream, Chocolate");
                System.out.print("Choose an ingredient (or type 'done' to finish): ");
                String ingredient = scanner.nextLine();

                if (ingredient.equalsIgnoreCase("done")) break;

                switch (ingredient.toLowerCase()) {
                    case "milk":
                        coffee = new MilkDecorator(coffee);
                        break;
                    case "caramel":
                        coffee = new CaramelDecorator(coffee);
                        break;
                    case "cream":
                        coffee = new CreamDecorator(coffee);
                        break;
                    case "chocolate":
                        coffee = new ChocolateDecorator(coffee);
                        break;
                    default:
                        System.out.println("Unknown ingredient. Try again.");
                }
            }
        }

        System.out.println("\nYour order:");
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Total cost: $" + coffee.getCost());
        System.out.println("Enjoy your coffee!");

        scanner.close();
    }
}
