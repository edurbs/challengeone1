package org.edurbs.challengeone1.application.usecases.domain;

import org.edurbs.challengeone1.application.domain.Currency;
import org.edurbs.challengeone1.application.domain.CurrencyRate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateTest {

    @Test
    void shouldCreateCurrencyRate() {
        var currencyRate = new CurrencyRate(Currency.USD, 1.0);
        assertEquals(Currency.USD, currencyRate.currency());
        assertEquals(1.0D, currencyRate.rate());
    }
}