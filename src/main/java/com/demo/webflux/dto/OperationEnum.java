package com.demo.webflux.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationEnum {

    plus("+"),
    minus("-"),
    multiply("*"),
    divide("/");

    private final String value;
}
