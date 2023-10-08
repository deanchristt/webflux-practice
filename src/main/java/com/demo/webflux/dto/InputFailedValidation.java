package com.demo.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputFailedValidation {

    private int errorCode;
    private int input;
    private String message;
}
