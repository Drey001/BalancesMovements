package co.com.balance.config;


import co.com.balance.model.balance.BalanceGateway;
import co.com.balance.model.movements.MovementsGateway;
import co.com.balance.usecase.balance.BalanceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public BalanceUseCase balanceUseCase(BalanceGateway balanceGateway, MovementsGateway movementsGateway) {
        return new BalanceUseCase(balanceGateway,movementsGateway);
    }

}
