package com.taxibooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bookmyride.config.BookMyRideConfiguration;
import com.bookmyride.enums.TaxiBookingStatus;
import com.bookmyride.taxi.request.TaxiBookingAcceptRequest;
import com.bookmyride.taxi.request.TaxiBookingRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxibooking.exception.BookingIdNotFoundException;
import com.taxibooking.model.TaxiBooking;
import com.taxibooking.repository.TaxiBookingRepository;

import reactor.core.publisher.Mono;

@Service
public class TaxiBookingService {
	@Autowired
	private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
    @Autowired
    private TaxiBookingRepository taxiBookingRepo;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public Mono<TaxiBooking> bookTaxi(TaxiBookingRequest taxiBookingRequest){
    	TaxiBooking taxiBooking = new TaxiBooking();
    	Point start = new Point(taxiBookingRequest.getStart().getLatitude(),taxiBookingRequest.getStart().getLongitude());
    	Point end = new Point(taxiBookingRequest.getEnd().getLatitude(),taxiBookingRequest.getEnd().getLongitude());
    	taxiBooking.setStart(start);
    	taxiBooking.setEnd(end);
    	taxiBooking.setBookedTime(taxiBookingRequest.getBookingTime());
    	taxiBooking.setCustomerId(taxiBookingRequest.getCustomerId());
    	taxiBooking.setBookingStatus(TaxiBookingStatus.ACTIVE);
    	TaxiBooking saveTaxiBooking = taxiBookingRepo.save(taxiBooking);
    	return reactiveRedisTemplate.opsForGeo().add("BookMyRideTaxies", start, taxiBooking.getTaxiBookingId()).flatMap(l -> Mono.just(saveTaxiBooking));
    }
    public Mono<TaxiBooking> acceptBooking(String taxiBookingId,TaxiBookingAcceptRequest taxiBookingAcceptRequest){
    	Optional<TaxiBooking> taxiBookingOptional = taxiBookingRepo.findById(taxiBookingId);
    	if (taxiBookingOptional.isPresent()) {
            TaxiBooking taxiBooking = taxiBookingOptional.get();
            taxiBooking.setTaxiId(taxiBookingAcceptRequest.getTaxiId());
            taxiBooking.setAcceptedTime(taxiBookingAcceptRequest.getAcceptedTime());
            return Mono.just(taxiBookingRepo.save(taxiBooking)).doOnSuccess( t -> {
            		try {
            			redisTemplate.convertAndSend(BookMyRideConfiguration.BOOKMYRIDE_CHANNEL, objectMapper.writeValueAsString(taxiBookingAcceptRequest));
            		}catch(Exception e) {
            			e.printStackTrace();
            		}
            });
    	}else {
    		throw new BookingIdNotFoundException("Booking with given id "+taxiBookingId+" not found");
    	}
    }
}
