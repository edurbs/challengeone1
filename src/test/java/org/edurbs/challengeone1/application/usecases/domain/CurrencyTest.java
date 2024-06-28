package org.edurbs.challengeone1.application.usecases.domain;

import org.edurbs.challengeone1.application.domain.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void shouldGetCode() {
        String code = Currency.USD.getCode();
        assertEquals("USD", code);
    }

    @Test
    void shouldGetName() {
        String name = Currency.USD.getName();
        assertEquals("United States Dollar", name);
    }

    @Test
    void shouldGetCountry() {
        String country = Currency.USD.getCountry();
        assertEquals("United States", country);
    }
}