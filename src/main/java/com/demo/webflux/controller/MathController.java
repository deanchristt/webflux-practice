package com.demo.webflux.controller;

import com.demo.webflux.dto.Response;
import com.demo.webflux.service.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("math")
public class MathController {

    private final MathService mathService;

    @GetMapping("/square/{input}")
    public Response findSquare(@PathVariable int input){
        return mathService.findSquare(input);
    }

    @GetMapping("/table/{input}")
    public List<Response> multiplicationTable(@PathVariable int input){
        return mathService.multiplicationTable(input);
    }

}
