package ua.com.foxminded.mtrestclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.mtrestclient.dto.CurrencyDTO;
import ua.com.foxminded.mtrestclient.repository.CurrencyRepository;
import ua.com.foxminded.mtrestclient.util.mapper.CurrencyMapper;

@Service
public class CurrencyService {

    private final CurrencyMapper mapper;
    private final CurrencyRepository repository;
    private final CurrencyApiService api;

    @Autowired
    public CurrencyService(CurrencyMapper mapper, CurrencyRepository repository, CurrencyApiService api) {
        this.mapper = mapper;
        this.repository = repository;
        this.api = api;
    }

    public CurrencyDTO findByCode(String code) {
        if (repository.findByCode(code).isEmpty()) {
            api.getCurrencyRate().forEach(this::save);
        }
        return mapper.toDto(repository.findByCode(code).orElseThrow(RuntimeException::new));
    }

    public CurrencyDTO save(CurrencyDTO dto) {
        repository.save(mapper.toEntity(dto));
        return dto;
    }

}
