package co.com.balance.model.movements;


import co.com.balance.model.account.Account;
import co.com.balance.model.accountRequest.AccountRequest;
import reactor.core.publisher.Mono;

public interface MovementsGateway {

    Mono<Movements> getMovements(AccountRequest accountRequest);

}
