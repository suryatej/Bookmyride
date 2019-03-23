package com.taxi.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@RedisHash("Taxi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taxi implements Serializable {
    @Id
    private String taxiId = UUID.randomUUID().toString();
    private String driver_Id = UUID.randomUUID().toString();
    private String type;
    private String status;
	
}
