package co.com.balance.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class Router {


@Bean
public RouterFunction<ServerResponse> routerFunction(Handler handler) {
    return route(GET("/api/usecase/balance"), handler::getBalance)
            .andRoute(GET("/api/usecase/movements"),handler::getMovements)
            .andRoute(GET("/api/usecase/balanceandmovements"),handler::getBalanceAndMovements);
        }
    }
