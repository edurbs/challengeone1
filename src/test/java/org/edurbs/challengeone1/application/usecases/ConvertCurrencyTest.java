package org.edurbs.challengeone1.application.usecases;

import org.edurbs.challengeone1.application.domain.Currency;
import org.edurbs.challengeone1.application.gateways.CurrencyGateway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ConvertCurrencyTest {

    private static ConvertCurrency convertCurrency;

    @BeforeAll
    static void setup(){
        CurrencyGateway currencyGatewayImpl = new CurrencyGateway() {
            @Override
            public double getCurrencyRate(Currency currency) {
                if(currency == Currency.USD){
                    return 1.0D;
                }else if(currency == Currency.BRL){
                    return 5.4020D;
                }if(currency == Currency.CLP){
                    return 945.7054;
                }
                return 0.0D;
            }
        };
        convertCurrency = new ConvertCurrency(currencyGatewayImpl);
    }


    @Test
    void shouldConvertFromBRLToCLP() {
        double result = convertCurrency.convert(Currency.BRL, Currency.CLP, 108.04);
        assertEquals(18914.11, result);
    }
    @Test
    void shouldConvertFromBRLToUSD() {
        double result = convertCurrency.convert(Currency.BRL, Currency.USD, 108.04);
        assertEquals(20, result);
    }
    @Test
    void shouldConvertFromCLPToUSD() {
        double result = convertCurrency.convert(Currency.CLP, Currency.USD, 18914.11);
        assertEquals(20, result);
    }
    @Test
    void shouldConvertFromCLPToBRL() {
        double result = convertCurrency.convert(Currency.CLP, Currency.BRL, 18914.11);
        assertEquals(108.04, result);
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenCurrencyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            convertCurrency.convert(null, Currency.USD, 108.04);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            convertCurrency.convert(Currency.AED, null, 108.04);
        });
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenAmountIsZeroNegativeOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            convertCurrency.convert(Currency.USD, Currency.BRL, -108.04);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            convertCurrency.convert(Currency.USD, Currency.BRL, 0D);
        });
        assertThrows(NullPointerException.class, () -> {
            convertCurrency.convert(Currency.USD, Currency.BRL, null);
        });
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenCurrencyIsInvalid(){
        assertThrows(IllegalArgumentException.class, () -> {
            convertCurrency.convert(Currency.AED, Currency.USD, 108.04);
        });
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenCurrencyRateIsZero(){
        var localCurrencyGatewayImpl = new CurrencyGateway() {
            @Override
            public double getCurrencyRate(Currency currency) {
                return 0D;
            }
        };
        var localConvertCurrency = new ConvertCurrency(localCurrencyGatewayImpl);

        assertThrows(IllegalArgumentException.class, () -> {
            localConvertCurrency.convert(Currency.BRL, Currency.CLP, 108.04);
        });
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenCurrenciesAreEquals(){
        assertThrows(IllegalArgumentException.class, () -> {
            convertCurrency.convert(Currency.USD, Currency.USD, 108.04);
        });
    }
}

