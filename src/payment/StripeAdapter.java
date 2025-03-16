package payment;

class StripeAdapter implements PaymentMethod {
    private final StripeAPI stripeAPI;

    public StripeAdapter(StripeAPI stripeAPI) {
        this.stripeAPI = stripeAPI;
    }

    @Override
    public String processPayment(double amount) {
        return stripeAPI.makePayment(amount);
    }

    @Override
    public String refundPayment(String transactionId, double amount) {
        return stripeAPI.refundPayment(transactionId, amount);
    }
}
