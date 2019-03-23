package com.taxibooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taxibooking.model.TaxiBooking;

@Repository
public interface TaxiBookingRepository extends CrudRepository<TaxiBooking, String>  {

}
