package br.com.caju.infrastructure.service;

import br.com.caju.application.gateway.SaveTransactionGateway;
import br.com.caju.core.domain.Transaction;
import br.com.caju.infrastructure.entity.TransactionEntity;
import br.com.caju.infrastructure.mapper.TransactionMapper;
import br.com.caju.infrastructure.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveTransactionGatewayImpl implements SaveTransactionGateway {
    
    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    
    public SaveTransactionGatewayImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }
    
    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.toTransactionEntity(transaction);
        transactionEntity = transactionRepository.save(transactionEntity);
        return transactionMapper.toTransaction(transactionEntity);
    }
}
