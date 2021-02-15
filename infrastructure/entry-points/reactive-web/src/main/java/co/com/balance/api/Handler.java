package co.com.balance.api;

import co.com.balance.model.account.Account;
import co.com.balance.model.accountRequest.AccountRequest;
import co.com.balance.model.balance.Balance;
import co.com.balance.model.movements.Movements;
import co.com.balance.usecase.balance.BalanceUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class Handler {

    private BalanceUseCase balanceUseCase;

    public Mono<ServerResponse> getBalance(ServerRequest serverRequest) {
        Mono<Balance>  result =  balanceUseCase.getBalanceAccount();
        return ServerResponse.ok().body(result, Balance.class);
    }

    public Mono<ServerResponse> getMovements(ServerRequest serverRequest) {

        Mono<Movements>  result =  balanceUseCase.geMovements();
        return ServerResponse.ok().body(result, Movements.class);

    }

    public Mono<ServerResponse> getBalanceAndMovements(ServerRequest serverRequest) {
        Map<String,String> parametros = null;
        try{
            parametros = splitQuery(serverRequest.uri());
        } catch (Exception e){
            parametros.clear();
            parametros.put("type","C");
            parametros.put("number","1234");
        }
        Mono<Account> result = balanceUseCase.getBalanceAndMovement(AccountRequest.builder()
                .tipo(parametros.get("type"))
                .numero(parametros.get("number"))
                .build());

        return ServerResponse.ok().body(result, Account.class);
    }
    public static Map<String, String> splitQuery(URI url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                    URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
