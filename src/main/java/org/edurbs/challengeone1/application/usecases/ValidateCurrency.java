package org.edurbs.challengeone1.application.usecases;

import org.edurbs.challengeone1.application.domain.Currency;

public class ValidateCurrency {

    public static boolean isValid(String currencyCode) {
        if(currencyCode == null || currencyCode.isEmpty()){
            return false;
        }
        for (Currency currency : Currency.values()) {
            if (currency.getCode().equals(currencyCode)) {
                return true;
            }
        }
        return false;
    }
}
