package com.taxibooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyride.taxi.request.TaxiBookingAcceptRequest;
import com.bookmyride.taxi.request.TaxiBookingRequest;
import com.bookmyride.taxi.response.TaxiBookingAcceptResponse;
import com.bookmyride.taxi.response.TaxiBookingResponse;
import com.taxibooking.service.TaxiBookingService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bookmyride/taxibooking")
public class TaxiBookingController {
    @Autowired
	private TaxiBookingService taxiBookingService;
    @PostMapping
    public Mono<TaxiBookingResponse> bookTaxi(@RequestBody TaxiBookingRequest taxiBookingRequest){
    	return taxiBookingService.bookTaxi(taxiBookingRequest).map(t -> new TaxiBookingResponse(t.getTaxiBookingId()));
    }
    @PutMapping("/{taxiBookingId}/accept")
    public Mono<TaxiBookingAcceptResponse> accept(@PathVariable("taxiBookingId") String taxiBookingId,@RequestBody TaxiBookingAcceptRequest acceptRequest){
    	return taxiBookingService.acceptBooking(taxiBookingId, acceptRequest).map(t -> new TaxiBookingAcceptResponse(t.getTaxiBookingId(),t.getTaxiId(),t.getAcceptedTime()) );
    }
}
