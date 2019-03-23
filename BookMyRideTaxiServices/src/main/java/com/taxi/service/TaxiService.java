package com.taxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import com.bookmyride.enums.TaxiStatus;
import com.bookmyride.taxi.request.Location;
import com.taxi.exception.TaxiNotExistsException;
import com.taxi.model.Taxi;
import com.taxi.repository.TaxiRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Service
public class TaxiService {
	@Autowired
	private TaxiRepository taxiRepo;
	
    @Autowired
	private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
    
    public Mono<Taxi> addTaxi(Taxi taxi){
		return Mono.just(taxiRepo.save(taxi));
    }
    
    public Mono<String> getTaxiStatus(String taxiId) {
        Optional<Taxi> taxiOptional = taxiRepo.findById(taxiId);
        if (taxiOptional.isPresent()) {
            Taxi taxi = taxiOptional.get();
            return Mono.just(taxi.getStatus());
        } else {
            throw new TaxiNotExistsException("Taxi with "+taxiId+" not exists");
        }

    }
    public Mono<Taxi> updateTaxiStatus(String taxiId, String taxiStatus) {
    	Optional<Taxi> taxiOptional = taxiRepo.findById(taxiId);
        if (taxiOptional.isPresent()) {
            Taxi taxi = taxiOptional.get();
            taxi.setStatus(taxiStatus);
            if(taxiStatus.equalsIgnoreCase(TaxiStatus.OCCUPIED.name())) {
            	reactiveRedisTemplate.opsForGeo().remove("BookMyRideTaxies", taxiId);
            }
            return Mono.just(taxiRepo.save(taxi));
        } else {
            throw new TaxiNotExistsException("Taxi with "+taxiId+" not exists");
        }
    }
    public Mono<Taxi> updateLocation(String taxiId,Location location){
    	Optional<Taxi> taxiOptional = taxiRepo.findById(taxiId);
        if (taxiOptional.isPresent()) {
            Taxi taxi = taxiOptional.get();
            return reactiveRedisTemplate.opsForGeo().add("BookMyRideTaxies", new Point(location.getLongitude(),location.getLatitude()), taxiId).flatMap(l -> Mono.just(taxi));
        } else {
            throw new TaxiNotExistsException("Taxi with "+taxiId+" not exists");
        }
    }
    public Flux<GeoResult<RedisGeoCommands.GeoLocation<String>>> getAvailableTaxis(Double latitude, Double longitude, Double radius) {
        return reactiveRedisTemplate.opsForGeo().radius("BookMyRideTaxies", new Circle(new Point(longitude, latitude), new Distance(radius, Metrics.KILOMETERS)));
    }
}
