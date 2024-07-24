package br.com.caju.infrastructure.config;

import br.com.caju.application.gateway.*;
import br.com.caju.application.usecaseimpl.*;
import br.com.caju.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    public FindUserByAccountIdUseCase findAccountByAccountIdUseCase(FindUserByAccountIdGateway findUserByAccountIdGateway){
        return new FindUserByAccountIdUseCaseImpl(findUserByAccountIdGateway);
    }

    @Bean
    public FindMerchantByNameUseCase findMerchantByNameUseCase(FindMerchantByNameGateway findMerchantByNameGateway){
        return new FindMerchantByNameUseCaseImpl(findMerchantByNameGateway);
    }

    @Bean
    public BalanceSufficientUseCase balanceSufficientUseCase(FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway){
        return new BalanceSufficientUseCaseImpl(findBenefitWalletByCategoryAndAccountIdGateway);
    }

    @Bean
    public DecreaseBalanceUseCase decreaseBalanceUseCase(FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway, SaveBenefitWalletGateway saveBenefitWalletGateway){
        return new DecreaseBalanceUseCaseImpl(findBenefitWalletByCategoryAndAccountIdGateway, saveBenefitWalletGateway);
    }

    @Bean
    public TransactionProcessUseCase processTransactionUseCase(FindUserByAccountIdUseCase findUserByAccountIdUseCase, FindMerchantByNameUseCase findMerchantByNameUseCase, BalanceSufficientUseCase balanceSufficientUseCase, DecreaseBalanceUseCase decreaseBalanceUseCase, SaveTransactionGateway saveTransactionGateway){
        return new TransactionProcessUseCaseImpl(findUserByAccountIdUseCase, findMerchantByNameUseCase, balanceSufficientUseCase, decreaseBalanceUseCase, saveTransactionGateway);
    }
}
