package br.com.caju.core.domain.enums;

import java.util.Collections;
import java.util.List;

public enum BenefitCategoryEnum {
    FOOD(List.of("5411", "5412"), "Alimentação"),
    MEAL(List.of("5811", "5812"), "Refeição"),
    CASH(Collections.emptyList(), "Saldo Livre");
    
    private final List<String> mccCodes;
    
    private final String description;
    
    BenefitCategoryEnum(List<String> mccCodes, String description) {
        this.mccCodes = mccCodes;
        this.description = description;
    }
    
    public List<String> getMccCodes() {
        return mccCodes;
    }
    
    public static BenefitCategoryEnum fromMcc(String mcc) {
        for (BenefitCategoryEnum type : BenefitCategoryEnum.values()) {
            if (type.getMccCodes().contains(mcc)) {
                return type;
            }
        }
        return CASH;
    }
}
