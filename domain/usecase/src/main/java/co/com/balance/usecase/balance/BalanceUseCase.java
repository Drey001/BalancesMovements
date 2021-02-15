package co.com.balance.usecase.balance;

import co.com.balance.model.account.Account;
import co.com.balance.model.accountRequest.AccountRequest;
import co.com.balance.model.balance.Balance;
import co.com.balance.model.balance.BalanceGateway;
import co.com.balance.model.movements.Movement;
import co.com.balance.model.movements.Movements;
import co.com.balance.model.movements.MovementsGateway;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;


public class BalanceUseCase {

    private BalanceGateway balanceGateway;
    private MovementsGateway movementsGateway;

    public BalanceUseCase(BalanceGateway balanceGateway,MovementsGateway movementsGateway) {
        this.balanceGateway = balanceGateway;
        this.movementsGateway = movementsGateway;
    }

    public Mono<Balance> getBalanceAccount(){

        return balanceGateway.getBalance(AccountRequest.builder().tipo("C").numero("12345").build());
    }

    public Mono<Movements> geMovements(){

        return movementsGateway.getMovements(AccountRequest.builder().tipo("C").numero("1234").build());
    }

    public Mono<Account> getBalanceAndMovement(AccountRequest accountRequest) {
        Mono<Tuple2<Balance,Movements>> tupla = Mono.zip(
                balanceGateway.getBalance(AccountRequest.builder()
                        .tipo(accountRequest.getTipo())
                        .numero(accountRequest.getNumero())
                        .build()),
                movementsGateway.getMovements(AccountRequest.builder()
                        .tipo(accountRequest.getTipo())
                        .numero(accountRequest.getNumero())
                        .build()));
        Mono<Account> account = tupla.flatMap( t -> {
            return Mono.just(Account.builder().balance(t.getT1()).movements(t.getT2()).build());
        });
        return account;
    }

}
