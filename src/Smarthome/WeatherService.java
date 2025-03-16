package Smarthome;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class WeatherService {
    private static final String API_KEY = "9c20bf45e4c173fe2535d491fd8db90f";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=" + API_KEY;

    public static double getTemperature(String city) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(API_URL, city)))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseTemperature(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private static double parseTemperature(String json) {
        int index = json.indexOf("\"temp\":");
        if (index != -1) {
            String tempStr = json.substring(index + 7, json.indexOf(',', index));
            return Double.parseDouble(tempStr);
        }
        return 0.0;
    }
}
