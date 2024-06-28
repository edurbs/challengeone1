package org.edurbs.challengeone1.application.usecases;

import jakarta.annotation.Nonnull;
import org.edurbs.challengeone1.application.domain.Currency;
import org.edurbs.challengeone1.application.domain.CurrencyRate;
import org.edurbs.challengeone1.application.gateways.CurrencyGateway;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertCurrency {

    private final CurrencyGateway currencyGateway;

    public ConvertCurrency(CurrencyGateway currencyGateway) {
        this.currencyGateway = currencyGateway;
    }

    public double convert(@Nonnull Currency currencyOrigin,@Nonnull Currency currencyDestination, @Nonnull Double amount){
        if(amount <= 0.0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if(currencyOrigin == currencyDestination){
            throw new IllegalArgumentException("Currencies must be different");
        }
        CurrencyRate currencyRateOrigin = loadCurrencyRate(currencyOrigin);
        CurrencyRate currencyRateDestination = loadCurrencyRate(currencyDestination);
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        BigDecimal bigDecimalOriginRate = BigDecimal.valueOf(currencyRateOrigin.rate());
        BigDecimal bigDecimalDestinationRate = BigDecimal.valueOf(currencyRateDestination.rate());
        return bigDecimalAmount
                .multiply(bigDecimalDestinationRate)
                .divide(bigDecimalOriginRate, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private CurrencyRate loadCurrencyRate(@Nonnull Currency currency) {
        double rate = this.currencyGateway.getCurrencyRate(currency);
        if(rate == 0.0) {
            throw new IllegalArgumentException("Currency rate cannot be zero");
        }
        return new CurrencyRate(currency, rate);
    }
}
