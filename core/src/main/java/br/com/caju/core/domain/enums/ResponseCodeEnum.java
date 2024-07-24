package br.com.caju.core.domain.enums;

public enum ResponseCodeEnum {
    APPROVED("00"),
    INSUFFICIENT_BALANCE("51"),
    ERROR("07");
    
    private final String code;
    
    ResponseCodeEnum(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
}
