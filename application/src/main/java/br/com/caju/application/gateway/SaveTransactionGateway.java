package br.com.caju.application.gateway;


import br.com.caju.core.domain.Transaction;

public interface SaveTransactionGateway {
    
    Transaction save(Transaction transaction);
}
