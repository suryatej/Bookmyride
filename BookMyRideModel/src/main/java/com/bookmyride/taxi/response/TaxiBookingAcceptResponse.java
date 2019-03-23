package com.bookmyride.taxi.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookingAcceptResponse {
	private String taxiBookingId;

    private String taxiId;

    private Date acceptedTime;
}
