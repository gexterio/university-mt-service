package ua.com.foxminded.mtrestclient.util.converter;

import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {

    public Double convertCurrencyToUSD( Double count, Double currency) {
        return count/currency;
    }

}
