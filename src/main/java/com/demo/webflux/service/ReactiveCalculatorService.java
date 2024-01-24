package com.demo.webflux.service;

import com.demo.webflux.dto.InputCalculatorDto;
import com.demo.webflux.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCalculatorService {

    public Mono<Response> plus(InputCalculatorDto req){
        return Mono.fromSupplier(() -> req.getA() + req.getB())
                .map(Response::new);
    }

    public Mono<Response> minus(InputCalculatorDto req){
        return Mono.fromSupplier(() -> req.getA() - req.getB())
                .map(Response::new);
    }

    public Mono<Response> times(InputCalculatorDto req){
        return Mono.fromSupplier(() -> req.getA() * req.getB())
                .map(Response::new);
    }

    public Mono<Response> divide(InputCalculatorDto req){
        return Mono.fromSupplier(() -> req.getA() / req.getB())
                .map(Response::new);
    }
}
