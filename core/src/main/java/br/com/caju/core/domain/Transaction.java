package br.com.caju.core.domain;

import br.com.caju.core.exception.TransactionException;
import br.com.caju.core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public class Transaction {
    private Long id;
    private String accountId;
    private BigDecimal amount;
    private String mcc;
    private String merchant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Transaction() {
    }
    
    public Transaction(Long id, String accountId, BigDecimal amount, String mcc, String merchant, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.mcc = mcc;
        this.merchant = merchant;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Transaction(String accountId, BigDecimal amount, String mcc, String merchant) {
        this.accountId = accountId;
        this.amount = amount;
        this.mcc = mcc;
        this.merchant = merchant;
        this.createdAt = LocalDateTime.now();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountId) throws TransactionException {
        if (isNull(accountId) || accountId.isBlank())
            throw new TransactionException(ErrorCodeEnum.TR002.getMessage(), ErrorCodeEnum.TR002.getCode());
        
        this.accountId = accountId;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) throws TransactionException {
        amountIsValid(amount);
        this.amount = amount;
    }
    
    public String getMcc() {
        return mcc;
    }
    
    public void setMcc(String mcc) throws TransactionException {
        isMccValid(mcc);
        this.mcc = mcc;
    }
    
    public String getMerchant() {
        return merchant;
    }
    
    public void setMerchant(String merchant) throws TransactionException {
        if (isNull(merchant) || merchant.isBlank())
            throw new TransactionException(ErrorCodeEnum.TR003.getMessage(), ErrorCodeEnum.TR003.getCode());
        
        this.merchant = merchant;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        
        Transaction that = (Transaction) o;
        
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!getAccountId().equals(that.getAccountId())) return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (!getMcc().equals(that.getMcc())) return false;
        return getMerchant().equals(that.getMerchant());
    }
    
    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getAccountId().hashCode();
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + getMcc().hashCode();
        result = 31 * result + getMerchant().hashCode();
        return result;
    }
    
    private void isMccValid(String mcc) throws TransactionException {
        if (isNull(mcc) || mcc.isBlank() || mcc.length() != 4)
            throw new TransactionException(ErrorCodeEnum.TR001.getMessage(), ErrorCodeEnum.TR001.getCode());
    }
    
    private void amountIsValid(BigDecimal amount) throws TransactionException {
        if (isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new TransactionException(ErrorCodeEnum.TR004.getMessage(), ErrorCodeEnum.TR004.getCode());
    }
}
