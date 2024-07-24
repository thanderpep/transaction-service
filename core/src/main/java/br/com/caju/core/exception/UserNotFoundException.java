package br.com.caju.core.exception;

public class UserNotFoundException extends Exception {
    
    private String code;
    
    public UserNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
