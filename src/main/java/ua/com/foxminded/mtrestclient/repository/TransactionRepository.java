package ua.com.foxminded.mtrestclient.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.mtrestclient.model.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

     Page<TransactionEntity> findAllByCustomerId(Long customerId, Pageable pageable);
}
