package br.com.caju.core.exception.enums;

public enum ErrorCodeEnum {

    AC001("Conta não encontrada", "AC-001"),
    
    BW001("Saldo insuficiente", "BA-001"),
    BW002("Tipo de benefício não encontrado", "BW-002"),
    
    TR001("MCC deve possuir 4 dígitos", "TR-001"),
    TR002("Conta não informada", "TR-002"),
    TR003("Comerciante não informada", "TR-003"),
    TR004("Valor a ser debitado deve ser maior que zero.", "TR-004"),
    
    ME001("Comerciante não encontrado", "ME-001"),
    
    GE001("Valor deve ser maior que zero.", "GE-001");
    
    private final String message;
    private final String code;
    
    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getCode() {
        return code;
    }
}
