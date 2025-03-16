package payment;

class Transaction {
    private final String transactionId;
    private final double amount;
    private final TransactionStatus status;

    public Transaction(String transactionId, double amount, TransactionStatus status) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("ID: %-20s |  Сумма: %-10s |  Статус: %s",
                transactionId, StripeAPI.Utils.formatAmount(amount), status);
    }
}
