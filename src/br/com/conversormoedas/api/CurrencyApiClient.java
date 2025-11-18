package br.com.conversormoedas.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyApiClient {

    private static final String API_KEY = "db0111f7eeaac6f1ba7d5a2d";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient client = HttpClient.newHttpClient();

    public String getRates(String baseCurrency) {
        try {
            String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            System.out.println("Erro ao consultar a API: " + e.getMessage());
            return null;
        }
    }
}
