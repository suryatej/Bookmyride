package com.bookmyride.taxi.request;

import java.util.Date;
import java.util.UUID;

import com.bookmyride.enums.TaxiType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookingRequest {
	private String taxiBookingId = UUID.randomUUID().toString();
	private Location start;

    private Location end;

    private Date bookingTime = new Date();

    private Long customerId;

    private TaxiType taxiType;
}
