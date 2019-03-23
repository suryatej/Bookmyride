package com.taxi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookmyride.taxi.response.ErrorResponse;
import com.taxi.exception.TaxiNotExistsException;

@ControllerAdvice
public class BaseContoller {
	@ExceptionHandler(TaxiNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleTaxiIdNotFoundException(TaxiNotExistsException e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
