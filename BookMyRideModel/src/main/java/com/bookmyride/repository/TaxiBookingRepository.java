package com.bookmyride.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookmyride.entity.TaxiBooking;

public interface TaxiBookingRepository extends CrudRepository<TaxiBooking,String> {

}
