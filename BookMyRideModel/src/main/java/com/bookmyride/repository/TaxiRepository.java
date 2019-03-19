package com.bookmyride.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookmyride.entity.Taxi;

public interface TaxiRepository extends CrudRepository<Taxi, Long>{
       List<Taxi> findByStatus(String status);
}
