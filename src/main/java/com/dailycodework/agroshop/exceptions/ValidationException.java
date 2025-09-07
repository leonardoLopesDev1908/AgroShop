package com.dailycodework.agroshop.exceptions;

import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ValidationException extends RuntimeException {
    
    private final BindingResult errors;
    

}
