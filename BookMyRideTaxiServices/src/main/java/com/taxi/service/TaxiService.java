package com.taxi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookmyride.entity.Taxi;
import com.bookmyride.repository.TaxiRepository;

@Service
public class TaxiService {
	private TaxiRepository taxiRepo;

	public TaxiService(TaxiRepository taxiRepo) {
		this.taxiRepo = taxiRepo;
	}
	public List<Taxi> getAvailableTaxi(String lat,String lon,String radius){
		return taxiRepo.findByStatus("Available");
	}
 
}
