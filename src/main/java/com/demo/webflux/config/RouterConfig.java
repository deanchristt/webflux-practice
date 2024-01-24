package com.demo.webflux.config;

import com.demo.webflux.dto.InputFailedValidation;
import com.demo.webflux.exception.InputValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final RequestHandler requestHandler;


    @Bean
    public RouterFunction<ServerResponse> highLevelRouter(){
        return RouterFunctions.route()
                .path("router", this::serverResponseRouterFunction) // router/square/2 -> serverResponseRouterHandlerFunction.squareHandler()
                .build();
    }


    private RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")),requestHandler::squareHandler)
                .GET("square/{input}", request -> ServerResponse.badRequest().bodyValue("Only 10-19 allowed"))
                .GET("table/{input}", requestHandler::tableHandler)
                .GET("table/{input}/stream", requestHandler::tableStreamHandler)
                .POST("multiply", requestHandler::multiplyHandler)
                .GET("square/{input}/validation", requestHandler::squareHandlerWithValidation)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (err, req) -> {
            InputValidationException ex = (InputValidationException) err;
            InputFailedValidation response = InputFailedValidation.builder()
                    .errorCode(ex.getErrorCode())
                    .input(ex.getInput())
                    .message(ex.getMessage())
                    .build();
            return ServerResponse.badRequest().bodyValue(response);

        };
    }
}
