package com.taxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyride.entity.Taxi;
import com.taxi.service.TaxiService;

@RestController
@RequestMapping("/taxi")
public class TaxiController {
    @Autowired
	private TaxiService taxiService;
	
    @GetMapping()
    public List<Taxi> getAllAvailableTaxies(){
    	return taxiService.getAvailableTaxi("", "", "");
    }
}
