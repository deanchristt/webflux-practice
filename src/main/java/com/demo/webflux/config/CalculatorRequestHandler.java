package com.demo.webflux.config;

import com.demo.webflux.dto.InputCalculatorDto;
import com.demo.webflux.dto.Response;
import com.demo.webflux.service.ReactiveCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CalculatorRequestHandler {

    private final ReactiveCalculatorService reactiveCalculatorService;

    public Mono<ServerResponse> plus(ServerRequest serverRequest){
        Mono<Response> responseMono = reactiveCalculatorService.plus(requestProcess(serverRequest));
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> minus(ServerRequest serverRequest){
        Mono<Response> responseMono = reactiveCalculatorService.minus(requestProcess(serverRequest));
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> divide(ServerRequest serverRequest){
        InputCalculatorDto req = requestProcess(serverRequest);

        if (req.getB() == 0){
            return ServerResponse.badRequest().bodyValue("b value must not be 0");
        }

        Mono<Response> responseMono = reactiveCalculatorService.divide(req);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> multiply(ServerRequest serverRequest){
        Mono<Response> responseMono = reactiveCalculatorService.times(requestProcess(serverRequest));
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public InputCalculatorDto requestProcess(ServerRequest serverRequest){
        return InputCalculatorDto.builder()
                .a(Integer.parseInt(serverRequest.pathVariable("inputA")))
                .b(Integer.parseInt(serverRequest.pathVariable("inputB")))
                .build();
    }
}
