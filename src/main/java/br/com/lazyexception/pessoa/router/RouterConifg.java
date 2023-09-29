package br.com.lazyexception.pessoa.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConifg {

    @Bean
    RouterFunction<ServerResponse> routes(PessoaHandler handler) {
        return route(POST("/pessoas").and(accept(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(GET("/pessoas/{id}"), handler::get)
                .andRoute(GET("/contagem-pessoas"), handler::count)
                .andRoute(GET("/pessoas"), handler::filter);
    }
}
