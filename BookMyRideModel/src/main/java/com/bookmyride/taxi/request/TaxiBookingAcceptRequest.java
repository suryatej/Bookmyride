package com.bookmyride.taxi.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookingAcceptRequest {
	private String taxiBookingId;

    private String taxiId;

    private Date acceptedTime = new Date();
}
