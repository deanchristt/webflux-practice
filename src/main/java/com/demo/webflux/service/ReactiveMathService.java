package com.demo.webflux.service;

import com.demo.webflux.dto.MultiplyRequest;
import com.demo.webflux.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input){
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1)) //Non-Blocking sleep
//                .doOnNext(i -> SleepUtil.sleepSeconds(1)) //Blocking sleep
                .doOnNext(i -> System.out.println("math-service processing : " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequest> multiplyRequest){
        return multiplyRequest
                .map(result -> new Response(result.getFirst() * result.getSecond()));
    }

}
