package ua.com.foxminded.mtrestclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyApiResponseDTO {
    @JsonProperty
    public Boolean success;
    @JsonProperty
    public Long timestamp;
    @JsonIgnoreProperties
    public String base;
    @JsonProperty
    public LocalDate date;
    @JsonProperty
    public Map<String, Double> rates;
}


