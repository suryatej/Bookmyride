package com.bookmyride.taxi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaxiLocationRequest {
   private String taxiId;
   private Location location;
}
