package br.com.caju.core.exception;

public class MerchantNotFoundException extends Exception {
    
    private String code;
    
    public MerchantNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
