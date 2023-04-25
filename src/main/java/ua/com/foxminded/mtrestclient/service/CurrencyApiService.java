package ua.com.foxminded.mtrestclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.foxminded.mtrestclient.dto.CurrencyApiResponseDTO;

@Service
public class CurrencyApiService {

    @Value("${com.currencyapi.token}")
    private String token;
    @Value("${com.currencyapi.url}")
    private String url;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public CurrencyApiResponseDTO getCurrencyRate() {
        log.warn(String.format("send request to external CurrencyApi. URL: %s", url));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apikey", token);
        ResponseEntity<CurrencyApiResponseDTO> response = new RestTemplate()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), CurrencyApiResponseDTO.class);
        log.info(String.format("get response from external CurrencyApi. response: %s", response.getBody()));
        return response.getBody();
    }
}
