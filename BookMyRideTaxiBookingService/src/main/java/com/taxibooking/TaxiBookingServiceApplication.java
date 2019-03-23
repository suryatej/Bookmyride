package com.taxibooking;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.bookmyride.config.BookMyRideConfiguration;
import com.taxibooking.repository.TaxiBookingRepository;

@SpringBootApplication
@Import({BookMyRideConfiguration.class})
public class TaxiBookingServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TaxiBookingServiceApplication.class, args);
	}
	@Bean
	public ApplicationRunner applicationRunner(TaxiBookingRepository taxiRepo) {
		return args ->{taxiRepo.deleteAll(); };
	}
}
