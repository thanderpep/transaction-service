package br.com.caju.core.exception;

public class TransactionException extends Exception {
    
    private String code;
    
    public TransactionException(String message, String code) {
        super(message);
        this.code = code;
    }
}
