package org.edurbs.challengeone1.infra.exchangerateapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.edurbs.challengeone1.application.domain.Currency;
import org.edurbs.challengeone1.application.gateways.CurrencyGateway;
import org.edurbs.challengeone1.infra.exchangerateapi.models.ExchangeRateResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ExchangeRateApi implements CurrencyGateway {

    private ExchangeRateResponse read() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        String url_str = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/USD";
        try {
            URL url = new URI(url_str).toURL();
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonObject jsonObj = JsonParser.parseReader(
                    new InputStreamReader((InputStream) request.getContent()))
                    .getAsJsonObject();
            String result = jsonObj.toString();
            return new ParseJson().execute(result);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getCurrencyRate(Currency currency) {
        ExchangeRateResponse response = this.read();
        return response.conversion_rates().get(currency.getCode());
    }
}
