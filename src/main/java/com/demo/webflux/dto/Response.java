package com.demo.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class Response {

    private Date date = new Date();
    private int output;


    public Response(int output) {
        this.output = output;
    }
}
