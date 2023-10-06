package com.demo.webflux.controller;

import com.demo.webflux.dto.Response;
import com.demo.webflux.service.MathService;
import com.demo.webflux.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reactive/math")
public class ReactiveMathController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping("/square/{input}")
    public Mono<Response> findSquare(@PathVariable int input){
        return reactiveMathService.findSquare(input);
    }

    @GetMapping("/table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return reactiveMathService.multiplicationTable(input);
    }

    @GetMapping(value = "/stream/table/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input){
        return reactiveMathService.multiplicationTable(input);
    }

}
