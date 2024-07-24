package br.com.caju.core.domain;


import br.com.caju.core.domain.enums.BusinessTypeEnum;

import java.time.LocalDateTime;

public class Merchant {
    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private BusinessTypeEnum businessType;
    private String mccDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Merchant() {
    }
    
    public Merchant(Long id, String name, String address, String contactNumber, String email, BusinessTypeEnum businessType, String mccDefault, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.businessType = businessType;
        this.mccDefault = mccDefault;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public BusinessTypeEnum getBusinessType() {
        return businessType;
    }
    
    public void setBusinessType(BusinessTypeEnum businessType) {
        this.businessType = businessType;
    }
    
    public String getMccDefault() {
        return mccDefault;
    }
    
    public void setMccDefault(String mccDefault) {
        this.mccDefault = mccDefault;
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
}

