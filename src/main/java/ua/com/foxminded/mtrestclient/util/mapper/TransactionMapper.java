package ua.com.foxminded.mtrestclient.util.mapper;

import org.mapstruct.Mapper;
import ua.com.foxminded.mtrestclient.dto.TransactionDTO;
import ua.com.foxminded.mtrestclient.model.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDto(TransactionEntity entity);

    TransactionEntity toEntity(TransactionDTO dto);
}
