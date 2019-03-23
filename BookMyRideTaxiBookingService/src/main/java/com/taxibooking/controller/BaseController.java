package com.taxibooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookmyride.taxi.response.ErrorResponse;
import com.taxibooking.exception.BookingIdNotFoundException;


@ControllerAdvice
public class BaseController {
	@ExceptionHandler(BookingIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaxiIdNotFoundException(BookingIdNotFoundException e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
