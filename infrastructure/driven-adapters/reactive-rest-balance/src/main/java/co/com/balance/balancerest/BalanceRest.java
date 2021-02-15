package co.com.balance.balancerest;


import co.com.balance.model.accountRequest.AccountRequest;
import co.com.balance.model.balance.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;

@Component
public class BalanceRest implements BalanceGateway {

    private WebClient webClient;

    @Value("${routesLF:/balance}")
    private String uri;

    public BalanceRest(@Value("${routesLF.urlBaseAttributes:https://balancesmovents.getsandbox.com:443}") String urlBase) {
        webClient = WebClient
                .builder()
                .baseUrl(urlBase)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Mono<Balance> getBalance(AccountRequest accountRequest) {
        return webClient.get()
                .uri(uri)
                .exchange()
                .flatMap(response -> response.bodyToMono(Balance.class));
    }
}
