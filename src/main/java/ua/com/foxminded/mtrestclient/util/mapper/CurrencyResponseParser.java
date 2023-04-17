package ua.com.foxminded.mtrestclient.util.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.mtrestclient.api.CurrencyApiResponse;
import ua.com.foxminded.mtrestclient.dto.CurrencyDTO;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyResponseParser {

    private final ObjectMapper objectMapper;

    @Autowired
    public CurrencyResponseParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CurrencyApiResponse mapToCurrencyApiResponse(String body) {

        try {
            return objectMapper.readValue(body, CurrencyApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CurrencyDTO> mapToCurrencyList(String body) {
        CurrencyApiResponse currencyApiResponse = mapToCurrencyApiResponse(body);
        List<CurrencyDTO> currencyList = new ArrayList<>();
        currencyApiResponse.getRates().forEach((code, value) -> currencyList.add(CurrencyDTO.builder().code(code).value(value).build()));
        currencyList.forEach(dto -> dto.setLastUpdated(ZonedDateTime.ofInstant(Instant.ofEpochMilli(currencyApiResponse.getTimestamp()), ZoneId.systemDefault())));
        return currencyList;
    }

}
