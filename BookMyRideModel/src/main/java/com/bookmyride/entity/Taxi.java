package com.bookmyride.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Taxi")
public class Taxi {
	@Id
    @GeneratedValue
    @Column(name = "taxiId", nullable = false)
    private Long taxiId;
	@Column(name = "driver_Id", nullable = false)
    private Long driver_Id;
	@Column(name = "type", length = 20, nullable = false)
    private String type;
	@Column(name = "status", length = 20, nullable = false)
    private String status;
	@Column(name = "curr_lat", nullable = false)
    private double curr_lat;
	@Column(name = "curr_lon", nullable = false)
    private double curr_lon;
	public Long getTaxiId() {
		return taxiId;
	}
	public void setTaxiId(Long taxiId) {
		this.taxiId = taxiId;
	}
	public Long getDriver_Id() {
		return driver_Id;
	}
	public void setDriver_Id(Long driver_Id) {
		this.driver_Id = driver_Id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getCurr_lat() {
		return curr_lat;
	}
	public void setCurr_lat(double curr_lat) {
		this.curr_lat = curr_lat;
	}
	public double getCurr_lon() {
		return curr_lon;
	}
	public void setCurr_lon(double curr_lon) {
		this.curr_lon = curr_lon;
	}
	
}
