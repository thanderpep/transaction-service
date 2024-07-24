package br.com.caju.infrastructure.mapper;

import br.com.caju.core.domain.Transaction;
import br.com.caju.infrastructure.dto.request.ProcessTransactionRequest;
import br.com.caju.infrastructure.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction toTransaction(ProcessTransactionRequest processTransactionRequest) {
        return new Transaction(
                processTransactionRequest.account(),
                processTransactionRequest.totalAmount(),
                processTransactionRequest.mcc(),
                processTransactionRequest.merchant()
        );
    }
    
    public TransactionEntity toTransactionEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getMcc(),
                transaction.getMerchant(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }
    
    public Transaction toTransaction(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getAccountId(),
                entity.getAmount(),
                entity.getMcc(),
                entity.getMerchant(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
