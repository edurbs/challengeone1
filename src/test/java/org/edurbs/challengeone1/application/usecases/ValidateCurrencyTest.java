package org.edurbs.challengeone1.application.usecases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCurrencyTest {

    @Test
    void shouldBeValid() {
        assertTrue(ValidateCurrency.isValid("BRL"));
    }

    @Test
    void shouldBeInvalid() {
        assertFalse(ValidateCurrency.isValid("123"));
    }
}