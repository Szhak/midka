package payment;

interface PaymentMethod {
    String processPayment(double amount);

    String refundPayment(String transactionId, double amount);
}
