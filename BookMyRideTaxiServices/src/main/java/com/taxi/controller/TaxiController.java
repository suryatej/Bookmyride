package com.taxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyride.taxi.request.Location;
import com.bookmyride.taxi.response.TaxiResponse;
import com.bookmyride.taxi.response.TaxiStatusResponse;
import com.taxi.model.Taxi;
import com.taxi.service.TaxiService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bookmyride/taxi")
public class TaxiController {
    @Autowired
	private TaxiService taxiService;
	
    @PostMapping
    public Mono<TaxiResponse> addTaxi(Taxi taxi){
    	return taxiService.addTaxi(taxi).map(t -> new TaxiResponse(t.getTaxiId()));
    }
    @GetMapping("/{taxiId}/status")
    public Mono<TaxiStatusResponse> getTaxiStatus(@PathVariable("taxiId") String taxiId) {
        return taxiService.getTaxiStatus(taxiId).map(status -> new TaxiStatusResponse(taxiId,status));
    }    
    @PutMapping("/{taxiId}/status")
    public Mono<TaxiStatusResponse> updateTaxiStatus(@PathVariable("taxiId") String taxiId,@RequestParam("status") String status) {
    	return taxiService.updateTaxiStatus(taxiId, status).map(taxi -> new TaxiStatusResponse(taxi.getTaxiId(),taxi.getStatus()));
    }
    @PutMapping("/{taxiId}/location")
    public Mono<TaxiResponse> updateLocation(@PathVariable("taxiId") String taxiId, @RequestBody Location location) {
        return taxiService.updateLocation(taxiId, location).map(t -> new TaxiResponse(t.getTaxiId()));
    }
    @GetMapping
    public Flux<TaxiResponse> getAvailableTaxies(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude, @RequestParam(value = "radius", defaultValue = "1") Double radius) {
    	Flux<GeoResult<RedisGeoCommands.GeoLocation<String>>> availableTaxis = taxiService.getAvailableTaxis(latitude, longitude, radius);
    	return availableTaxis.map(t -> new TaxiResponse(t.getContent().getName()));
    }
    
}
