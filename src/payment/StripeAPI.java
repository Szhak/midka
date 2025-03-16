package payment;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class StripeAPI {
    private static final String API_URL = "https://api.stripe.com/v1/charges";
    private static final String REFUND_URL = "https://api.stripe.com/v1/refunds";
    private static final String SECRET_KEY = "sk_test_51R3CXKKlkbjUR1CdgvV9N45IMkvvTZAQroLsne4uzAHVC5lIqL3imHW5WZtzZi6TUkSubLAaC5hLzK7jCLiO8j0x00KEQ1DtBj";

    public String makePayment(double amount) {
        if (amount <= 0) return "Ошибка: Сумма должна быть больше 0.";

        try {
            HttpClient client = HttpClient.newHttpClient();
            String requestBody = "amount=" + (int) (amount * 100) + "&currency=usd&source=tok_visa";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + SECRET_KEY)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() == 200) {
                String chargeId = extractChargeId(response.body());
                return " Оплата " + Utils.formatAmount(amount) + "$ успешна. Charge ID: " + chargeId;
            } else {
                return " Ошибка Stripe ";
            }
        } catch (Exception e) {
            return "Ошибка обработки платежа Stripe: " + e.getMessage();
        }
    }

    public String refundPayment(String transactionId, double amount) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String requestBody = "charge=" + transactionId + "&amount=" + (int) (amount * 100);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(REFUND_URL))
                    .header("Authorization", "Bearer " + SECRET_KEY)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return "Возврат " + Utils.formatAmount(amount) + "$ успешен (ID: " + transactionId + ").";
            } else {
                return " Ошибка Stripe при возврате: ";
            }
        } catch (Exception e) {
            return "Ошибка возврата: " + e.getMessage();
        }
    }

    private String extractChargeId(String responseBody) {
        int start = responseBody.indexOf("\"id\": \"") + 7;
        int end = responseBody.indexOf("\"", start);
        return responseBody.substring(start, end);
    }

    // Вспомогательный класс для форматирования суммы
    static class Utils {
        public static String formatAmount(double amount) {
            return String.format("%.2f", amount);
        }
    }

}
