package org.edurbs.challengeone1.infra.exchangerateapi;

import com.google.gson.Gson;
import org.edurbs.challengeone1.infra.exchangerateapi.models.ExchangeRateResponse;

public class ParseJson {
    public ExchangeRateResponse execute(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, ExchangeRateResponse.class);
    }
}
