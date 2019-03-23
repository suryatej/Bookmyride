package com.bookmyride.taxi.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaxiRequest {
	private String taxiId = UUID.randomUUID().toString();
	private String driverId = UUID.randomUUID().toString();
	private String type;
}
