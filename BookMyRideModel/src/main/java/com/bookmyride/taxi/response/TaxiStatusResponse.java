package com.bookmyride.taxi.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiStatusResponse {
	private String taxiId;
    private String status;
}
