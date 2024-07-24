package br.com.caju.infrastructure.entity;

import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BENEFIT_WALLETS")
public class BenefitWalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "decimal(10,2) default 0.00", nullable = false)
    private BigDecimal balance;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BenefitCategoryEnum category;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
