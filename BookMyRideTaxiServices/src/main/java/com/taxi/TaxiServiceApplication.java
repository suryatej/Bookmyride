package com.taxi;

import java.util.UUID;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import com.bookmyride.config.BookMyRideConfiguration;
import com.bookmyride.enums.TaxiStatus;
import com.bookmyride.enums.TaxiType;
import com.bookmyride.taxi.request.Location;
import com.taxi.listener.TaxiBookingMessageListener;
import com.taxi.model.Taxi;
import com.taxi.repository.TaxiRepository;
import com.taxi.service.TaxiService;


@SpringBootApplication
@Import({BookMyRideConfiguration.class})
public class TaxiServiceApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TaxiServiceApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(TaxiRepository taxiRepository, TaxiService taxiService) {
		return args -> {
			taxiRepository.deleteAll();

			taxiRepository.save(new Taxi(UUID.randomUUID().toString(),UUID.randomUUID().toString(), TaxiType.SEDEN.name(),TaxiStatus.AVAILABLE.name() ));
			taxiRepository.save(new Taxi(UUID.randomUUID().toString(),UUID.randomUUID().toString(), TaxiType.MPV.name(), TaxiStatus.AVAILABLE.name()));
			taxiRepository.save(new Taxi(UUID.randomUUID().toString(),UUID.randomUUID().toString(), TaxiType.SUV.name(), TaxiStatus.AVAILABLE.name()));

			Iterable<Taxi> taxis = taxiRepository.findAll();

			taxis.forEach(t -> {
				taxiService.updateLocation(t.getTaxiId(), new Location(1.351616,103.808053)).subscribe();
			});
		};
	}
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,TaxiBookingMessageListener bookingListener) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(bookingListener,new PatternTopic(BookMyRideConfiguration.BOOKMYRIDE_CHANNEL));
		return container;
	}
}
