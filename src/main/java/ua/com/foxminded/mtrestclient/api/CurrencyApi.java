package ua.com.foxminded.mtrestclient.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.foxminded.mtrestclient.dto.CurrencyDTO;
import ua.com.foxminded.mtrestclient.util.mapper.CurrencyResponseParser;

import java.util.List;

@Component
public class CurrencyApi {
    @Value("${com.currencyapi.token}")
    private String token;
    @Value("${com.currencyapi.url}")
    private String url;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CurrencyResponseParser parser;

    HttpHeaders httpHeaders = new HttpHeaders();


    @Autowired
    public CurrencyApi(CurrencyResponseParser objMapper) {
        this.parser = objMapper;
    }

    public List<CurrencyDTO> getCurrencyRate() {
        log.warn(String.format("send request to external CurrencyApi. URL: %s", url));
        httpHeaders.set("apikey", token);
        ResponseEntity<String> response = new RestTemplate()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
        log.info(String.format("get response from external CurrencyApi. response: %s", response.getBody()));
        return parser.mapToCurrencyList(response.getBody());
    }

}
