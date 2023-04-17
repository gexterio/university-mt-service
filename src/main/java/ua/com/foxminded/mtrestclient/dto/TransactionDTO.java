package ua.com.foxminded.mtrestclient.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionDTO {

    private Long id;

    private Double count;

    private String currencyName;

    private Long customerId;

    private Double usdCount;
}
