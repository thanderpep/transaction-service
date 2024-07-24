package br.com.caju.core.exception;

public class BenefitWalletNotFoundException extends Exception {
    
    private String code;
    
    public BenefitWalletNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
