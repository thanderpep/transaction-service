package br.com.caju.core.domain;

import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.core.exception.InsufficientBalanceException;
import br.com.caju.core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public class BenefitWallet {
    private Long id;
    private String name;
    private BigDecimal balance;
    private BenefitCategoryEnum category;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public BenefitWallet() {
    }
    
    public BenefitWallet(Long id, String name, BigDecimal balance, BenefitCategoryEnum category, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.category = category;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public BenefitCategoryEnum getCategory() {
        return category;
    }
    
    public void setCategory(BenefitCategoryEnum category) {
        this.category = category;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void creditValue(BigDecimal value) {
        valueIsValid(value);
        this.balance = this.balance.add(value);
    }
    
    public void debitValue(BigDecimal value) throws InsufficientBalanceException {
        valueIsValid(value);
        
        if (this.balance.compareTo(value) < 0)
            throw new InsufficientBalanceException(ErrorCodeEnum.BW001.getMessage(), ErrorCodeEnum.BW001.getCode());
    
        this.balance = this.balance.subtract(value);
    }
    
    private void valueIsValid(BigDecimal amount) {
        if (isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(ErrorCodeEnum.GE001.getMessage());
    }
}
