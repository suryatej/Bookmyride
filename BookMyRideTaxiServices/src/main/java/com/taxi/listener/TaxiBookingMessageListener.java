package com.taxi.listener;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.bookmyride.enums.TaxiStatus;
import com.bookmyride.taxi.request.TaxiBookingAcceptRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.service.TaxiService;
@Component 
public class TaxiBookingMessageListener implements MessageListener  {
    @Autowired
	private TaxiService taxiService;
    private final ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			TaxiBookingAcceptRequest taxiBookingRequest = objectMapper.readValue(new String(message.getBody()),TaxiBookingAcceptRequest.class);
			taxiService.updateTaxiStatus(taxiBookingRequest.getTaxiId(), TaxiStatus.OCCUPIED.name());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
