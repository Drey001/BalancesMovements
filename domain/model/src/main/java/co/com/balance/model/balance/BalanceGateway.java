package co.com.balance.model.balance;

import co.com.balance.model.accountRequest.AccountRequest;
import reactor.core.publisher.Mono;

public interface BalanceGateway {

    Mono<Balance> getBalance(AccountRequest accountRequest);
}
