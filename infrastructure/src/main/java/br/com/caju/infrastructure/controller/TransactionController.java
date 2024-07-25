package br.com.caju.infrastructure.controller;

import br.com.caju.core.domain.Transaction;
import br.com.caju.core.domain.enums.ResponseCodeEnum;
import br.com.caju.core.exception.TransactionException;
import br.com.caju.infrastructure.dto.request.ProcessTransactionRequest;
import br.com.caju.infrastructure.dto.response.ProcessTransactionResponse;
import br.com.caju.infrastructure.mapper.TransactionMapper;
import br.com.caju.usecase.TransactionProcessUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    private TransactionProcessUseCase transactionProcessUseCase;
    private TransactionMapper transactionMapper;
    
    public TransactionController(TransactionProcessUseCase transactionProcessUseCase, TransactionMapper transactionMapper) {
        this.transactionProcessUseCase = transactionProcessUseCase;
        this.transactionMapper = transactionMapper;
    }
    
    @PostMapping
    public ResponseEntity<ProcessTransactionResponse> processTransaction(@RequestBody ProcessTransactionRequest request) throws TransactionException {
        ResponseCodeEnum resultCode = transactionProcessUseCase.processTransaction(transactionMapper.toTransaction(request));
        ProcessTransactionResponse response = new ProcessTransactionResponse(resultCode.getCode());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
