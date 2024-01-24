package com.demo.webflux.config;

import com.demo.webflux.dto.InputFailedValidation;
import com.demo.webflux.dto.MultiplyRequest;
import com.demo.webflux.dto.Response;
import com.demo.webflux.exception.InputValidationException;
import com.demo.webflux.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RequestHandler {

    private final ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> responseMono = reactiveMathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class); // For Publisher Type
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = reactiveMathService.multiplicationTable(input);
        return ServerResponse.ok().body(responseFlux, Response.class); // For Publisher Type
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = reactiveMathService.multiplicationTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, Response.class); // For Publisher Type
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest){
        Mono<MultiplyRequest> multiplyRequestMono = serverRequest.bodyToMono(MultiplyRequest.class);
        Mono<Response> responseMono = reactiveMathService.multiply(multiplyRequestMono);
        return ServerResponse.ok()
                .body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));

        //Validation
        if ((input <= 10 || input >= 20)){
            return Mono.error(new InputValidationException(input));
        }

        Mono<Response> responseMono = reactiveMathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class); // For Publisher Type
    }
}
