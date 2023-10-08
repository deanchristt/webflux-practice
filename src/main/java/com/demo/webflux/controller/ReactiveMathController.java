package com.demo.webflux.controller;

import com.demo.webflux.dto.MultiplyRequest;
import com.demo.webflux.dto.Response;
import com.demo.webflux.service.MathService;
import com.demo.webflux.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequest> multiplyRequest,
                                   @RequestHeader(required = false) Map<String, String> headers){
        /*
        Account account -> the account is deserialized without blocking before the controller is invoked.
        Mono<Account> -> the controller can use the Mono to declare logic to be executed after the account is deserialized.
         */

        System.out.println(headers);
        return reactiveMathService.multiply(multiplyRequest);
    }

}
