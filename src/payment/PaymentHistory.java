package payment;

import java.util.ArrayList;
import java.util.List;

class PaymentHistory {
    private final List<Transaction> transactions = new ArrayList<>();

    public void printHistory() {
        System.out.println("\n История платежей:");
        if (transactions.isEmpty()) {
            System.out.println(" Нет записей.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println("   ➤ " + transaction);
            }
        }
    }
}
