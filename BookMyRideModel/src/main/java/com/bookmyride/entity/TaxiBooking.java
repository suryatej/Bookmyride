package com.bookmyride.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Taxi_booking")
public class TaxiBooking {
	@Id
    @GeneratedValue
    @Column(name = "taxiBookingId", nullable = false)
    private Long taxiBookingId;
	@Column(name = "start_lat", nullable = false)
    private double start_lat;
	@Column(name = "start_lon", nullable = false)
    private double start_lon;
	@Column(name = "start_time", nullable = false)
	private Date start_time;
	@Column(name = "end_lat", nullable = false)
	private double end_lat;
	@Column(name = "end_lon", nullable = false)
	private double end_lon;
	@Column(name = "end_time", nullable = true)
	private Date end_time;
	@Column(name = "bookedTime", nullable = true)
	private Date bookedTime;
	@Column(name = "acceptedTime", nullable = true)
	private Date acceptedTime;
	@Column(name = "customerId", length = 20, nullable = false)
    private String customerId;
	@Column(name = "bookingStatus", length = 10, nullable = false)
    private String bookingStatus;
	@Column(name = "cancelTime", nullable = true)
	private Date cancelTime;
	@ManyToOne(targetEntity = Taxi.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "taxiId", referencedColumnName = "taxiId")
	private Taxi taxiId;
}
