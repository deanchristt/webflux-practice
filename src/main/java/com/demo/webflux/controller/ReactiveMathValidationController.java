package com.demo.webflux.controller;

import com.demo.webflux.dto.MultiplyRequest;
import com.demo.webflux.dto.Response;
import com.demo.webflux.exception.InputValidationException;
import com.demo.webflux.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("reactive/math/throw")
public class ReactiveMathValidationController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping("/square/{input}")
    public Mono<Response> findSquare(@PathVariable int input){
        if (!(input >= 10 && input <= 20)){
            throw new InputValidationException(input);
        }
        return reactiveMathService.findSquare(input);
    }
}
