package payment;

import java.util.*;


public class PaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentHistory history = new PaymentHistory();
        PaymentMethod stripePayment = PaymentFactory.createPayment("stripe");


        while (true) {
            System.out.println("\n Меню:");
            System.out.println("1️ Оплатить");
            System.out.println("2 Возврат");
            System.out.println("3 История");
            System.out.println("0️ Выход");
            System.out.print("Введите ваш выбор: ");

            try {
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 0 -> {
                        System.out.println("Выход...");
                        history.printHistory();
                        return;
                    }
                    case 1 -> {
                        System.out.print(" Введите сумму для оплаты: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        String result = stripePayment.processPayment(amount);
                        System.out.println(result);
                    }
                    case 2 -> {
                        System.out.print(" Введите ID транзакции для возврата: ");
                        String transactionId = scanner.nextLine();
                        System.out.print(" Введите сумму для возврата: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        String result = stripePayment.refundPayment(transactionId, amount);
                        System.out.println(result);
                    }
                    case 3 -> history.printHistory();
                    default -> System.out.println("️ Ошибка: Неверный ввод!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Попробуйте снова.");
                scanner.nextLine();
            }
        }
    }
}
