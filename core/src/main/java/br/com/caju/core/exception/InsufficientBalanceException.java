package br.com.caju.core.exception;

public class InsufficientBalanceException extends Exception {
    
    private String code;
    
    public InsufficientBalanceException(String message, String code) {
        super(message);
        this.code = code;
    }
}
