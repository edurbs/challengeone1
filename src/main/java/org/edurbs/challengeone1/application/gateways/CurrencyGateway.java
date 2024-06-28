package org.edurbs.challengeone1.application.gateways;

import org.edurbs.challengeone1.application.domain.Currency;

public interface CurrencyGateway {
    double getCurrencyRate(Currency currency);
}
