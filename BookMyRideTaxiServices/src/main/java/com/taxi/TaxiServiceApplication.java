package com.taxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"com.taxi.*"})
@EntityScan("com.bookmyride.entity")
@EnableJpaRepositories("com.bookmyride.repository")
public class TaxiServiceApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TaxiServiceApplication.class, args);
	}

}
