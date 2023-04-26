package ua.com.foxminded.mtrestclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class CurrencyDTO {

    private Long id;

    private String code;

    private Double value;

    private ZonedDateTime lastUpdated;

}
