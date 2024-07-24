package br.com.caju.core.domain.enums;

public enum BusinessTypeEnum {
    RESTAURANT("Restaurante"),
    SUPERMARKET("Supermercado"),
    RETAIL("Varejo"),
    WHOLESALE("Atacado"),
    ENTERTAINMENT("Entretenimento"),
    HEALTHCARE("Saúde"),
    EDUCATION("Educação"),
    TRANSPORTATION("Transporte"),
    FINANCE("Finanças"),
    OTHER("Outro");
    
    private final String type;
    
    BusinessTypeEnum(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}

