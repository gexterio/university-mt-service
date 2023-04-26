package ua.com.foxminded.mtrestclient.util.mapper;

import org.mapstruct.Mapper;
import ua.com.foxminded.mtrestclient.dto.CurrencyDTO;
import ua.com.foxminded.mtrestclient.model.CurrencyEntity;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDTO toDto(CurrencyEntity entity);

    CurrencyEntity toEntity(CurrencyDTO dto);
}
