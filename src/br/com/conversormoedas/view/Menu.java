package br.com.conversormoedas.view;

import br.com.conversormoedas.api.CurrencyApiClient;
import br.com.conversormoedas.api.CurrencyRateResponse;
import br.com.conversormoedas.model.CurrencyConverter;
import com.google.gson.Gson;

import java.util.Locale;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final CurrencyApiClient apiClient = new CurrencyApiClient();
    private final CurrencyConverter converter = new CurrencyConverter();
    private final Gson gson = new Gson();

    public void iniciar() {
        System.out.println("=== Conversor de Moedas ===");

        boolean executando = true;

        while (executando) {
            exibirOpcoes();

            String entrada = lerLinha("Opção: ");
            int opcao;
            try {
                opcao = Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número entre 0 e 6.");
                continue;
            }

            if (opcao == 0) {
                executando = false;
                System.out.println("Encerrando o programa...");
            } else if (opcao >= 1 && opcao <= 6) {
                processarOpcao(opcao);
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

    }

    private void exibirOpcoes() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Real → Dólar");
        System.out.println("2 - Real → Euro");
        System.out.println("3 - Real → Libra");
        System.out.println("4 - Dólar → Real");
        System.out.println("5 - Euro → Real");
        System.out.println("6 - Libra → Real");
        System.out.println("0 - Sair");
    }

    private void processarOpcao(int opcao) {
        String moedaOrigem;
        String moedaDestino;
        String baseParaApi;

        if (opcao == 1) {
            moedaOrigem = "BRL";
            moedaDestino = "USD";
            baseParaApi = "USD";
        } else if (opcao == 2) {
            moedaOrigem = "BRL";
            moedaDestino = "EUR";
            baseParaApi = "USD";
        } else if (opcao == 3) {
            moedaOrigem = "BRL";
            moedaDestino = "GBP";
            baseParaApi = "USD";
        } else if (opcao == 4) {
            moedaOrigem = "USD";
            moedaDestino = "BRL";
            baseParaApi = "USD";
        } else if (opcao == 5) {
            moedaOrigem = "EUR";
            moedaDestino = "BRL";
            baseParaApi = "USD";
        } else { // opcao == 6
            moedaOrigem = "GBP";
            moedaDestino = "BRL";
            baseParaApi = "USD";
        }

        double valor;
        try {
            String s = lerLinha("Digite o valor para converter (" + moedaOrigem + "): ");
            s = s.replace(",", ".");
            valor = Double.parseDouble(s);
            if (valor < 0) {
                System.out.println("Valor deve ser positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Use apenas números (ex: 100 ou 99.95).");
            return;
        }

        String json = apiClient.getRates(baseParaApi);
        if (json == null) {
            System.out.println("Não foi possível obter as taxas da API. Tente novamente mais tarde.");
            return;
        }

        CurrencyRateResponse dadosApi;
        try {
            dadosApi = gson.fromJson(json, CurrencyRateResponse.class);
        } catch (Exception e) {
            System.out.println("Erro ao processar resposta da API: " + e.getMessage());
            return;
        }

        try {
            double resultado = converter.converter(valor, moedaOrigem, moedaDestino, dadosApi);
            String formato = String.format(Locale.getDefault(), "%.2f", resultado);
            System.out.println("\nResultado: " + formato + " " + moedaDestino + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na conversão: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao converter: " + e.getMessage());
        }
    }

    private String lerLinha(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}