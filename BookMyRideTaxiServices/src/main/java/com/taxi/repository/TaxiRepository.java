package com.taxi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taxi.model.Taxi;

@Repository
public interface TaxiRepository extends CrudRepository<Taxi, String>{

}
