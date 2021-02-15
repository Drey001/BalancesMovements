package co.com.balance.movementsrest;

import co.com.balance.model.accountRequest.AccountRequest;
import co.com.balance.model.balance.Balance;
import co.com.balance.model.movements.Movements;
import co.com.balance.model.movements.MovementsGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MovementsRest implements MovementsGateway {

    private WebClient webClient;

    @Value("${routesLF:/movements}")
    private String uri;

    public MovementsRest(@Value("${routesLF.urlBaseAttributes:https://balancesmovents.getsandbox.com:443}") String urlBase) {
        webClient = WebClient
                .builder()
                .baseUrl(urlBase)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Mono<Movements> getMovements(AccountRequest accountRequest) {
        return webClient
                .get()
                .uri(uri)
                .exchange()
                .flatMap(response -> response.bodyToMono(Movements.class));
    }
}
