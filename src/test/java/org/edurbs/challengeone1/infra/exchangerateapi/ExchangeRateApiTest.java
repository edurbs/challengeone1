package org.edurbs.challengeone1.infra.exchangerateapi;

import org.edurbs.challengeone1.application.domain.Currency;
import org.edurbs.challengeone1.infra.exchangerateapi.models.ExchangeRateResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateApiTest {

    @Test
    void shouldGetCurrencyRate() {
        Double exchangeRate = new ExchangeRateApi().getCurrencyRate(Currency.USD);
        assertEquals(1.0D, exchangeRate);
    }
}