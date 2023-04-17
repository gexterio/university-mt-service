package ua.com.foxminded.mtrestclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.foxminded.mtrestclient.dto.TransactionDTO;
import ua.com.foxminded.mtrestclient.repository.TransactionRepository;
import ua.com.foxminded.mtrestclient.util.converter.CurrencyConverter;
import ua.com.foxminded.mtrestclient.util.mapper.TransactionMapper;

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

    public Page<TransactionDTO> getAllTransactionsForCustomer(Long customerId, Pageable pageable) {
        Double gelCurrency = currencyService.findByCode(nationalCurrency).getValue();
        Page<TransactionDTO> transactionsPage = repository.findAllByCustomerId(customerId, pageable)
                .map(mapper::toDto);
        for (TransactionDTO dto : transactionsPage) {
            dto.setUsdCount(converter.convertCurrencyToUSD(dto.getCount(), gelCurrency));
        }
        return transactionsPage;
    }
}
