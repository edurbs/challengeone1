package org.edurbs.challengeone1;

import org.edurbs.challengeone1.application.gateways.CurrencyGateway;
import org.edurbs.challengeone1.infra.exchangerateapi.ExchangeRateApi;
import org.edurbs.challengeone1.presentation.console.TextModeView;

public class Main {
    public static void main(String[] args) {
        CurrencyGateway currencyGateway = new ExchangeRateApi();
        new TextModeView(currencyGateway).start();
    }
}