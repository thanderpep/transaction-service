package br.com.caju.infrastructure.entity;

import br.com.caju.core.domain.enums.BusinessTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MERCHANTS")
public class MerchantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String address;
    
    private String contactNumber;
    
    private String email;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessTypeEnum businessType;
    
    private String mccDefault;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
