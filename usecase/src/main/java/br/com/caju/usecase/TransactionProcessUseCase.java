package br.com.caju.usecase;


import br.com.caju.core.domain.Transaction;
import br.com.caju.core.domain.enums.ResponseCodeEnum;

public interface TransactionProcessUseCase {
    
    ResponseCodeEnum processTransaction(Transaction transaction);
}
