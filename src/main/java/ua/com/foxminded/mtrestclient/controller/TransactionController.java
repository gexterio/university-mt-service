package ua.com.foxminded.mtrestclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.mtrestclient.dto.TransactionDTO;
import ua.com.foxminded.mtrestclient.service.TransactionService;

@RestController
@RequestMapping("/api/v2/users")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}/transactions")
    public Page<TransactionDTO> getTransaction(@PathVariable("id") Long id, Pageable pageable) {
        return transactionService.getAllTransactionsForCustomer(id, pageable);

    }
}
