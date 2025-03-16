package payment;

// Фабричный метод
class PaymentFactory {
    public static PaymentMethod createPayment(String method) {
        if ("stripe".equalsIgnoreCase(method)) {
            return new StripeAdapter(new StripeAPI());
        }
        throw new IllegalArgumentException(" Ошибка: Доступен только Stripe.");
    }
}
