package ua.com.foxminded.mtrestclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.foxminded.mtrestclient.dto.TransactionDTO;
import ua.com.foxminded.mtrestclient.repository.TransactionRepository;
import ua.com.foxminded.mtrestclient.util.converter.CurrencyConverter;
import ua.com.foxminded.mtrestclient.util.mapper.TransactionMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final CurrencyService currencyService;
    private final CurrencyConverter converter;
    @Value("${com.currencyapi.national-currency}")
    private String nationalCurrency;


    @Autowired
    public TransactionService(TransactionRepository repository, TransactionMapper mapper, CurrencyService currencyService, CurrencyConverter converter) {
        this.repository = repository;
        this.mapper = mapper;
        this.currencyService = currencyService;
        this.converter = converter;
    }

    public List<TransactionDTO> getAllTransactionsForCustomer(Long customerId) {
        Double gelCurrency = currencyService.findByCode(nationalCurrency).getValue();
        return repository.findAllByCustomerId(customerId).stream()
                .map(entity -> {
                    TransactionDTO dto = mapper.toDto(entity);
                    dto.setUsdCount(converter.convertCurrencyToUSD(dto.getCount(), gelCurrency));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
