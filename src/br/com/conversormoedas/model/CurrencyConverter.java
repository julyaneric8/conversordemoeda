package br.com.conversormoedas.model;

import br.com.conversormoedas.api.CurrencyRateResponse;

public class CurrencyConverter {

    public double converter(
            double valor,
            String moedaOrigem,
            String moedaDestino,
            CurrencyRateResponse dadosApi
    ) {

        Double taxaDestino = dadosApi.getConversion_rates().get(moedaDestino);

        if (taxaDestino == null) {
            throw new IllegalArgumentException("Moeda destino inválida: " + moedaDestino);
        }

        if (!dadosApi.getBase_code().equalsIgnoreCase(moedaOrigem)) {
            Double taxaOrigem = dadosApi.getConversion_rates().get(moedaOrigem);

            if (taxaOrigem == null) {
                throw new IllegalArgumentException("Moeda origem inválida: " + moedaOrigem);
            }

            double valorEmBase = valor / taxaOrigem;

            return valorEmBase * taxaDestino;
        }

        return valor * taxaDestino;
    }
}

