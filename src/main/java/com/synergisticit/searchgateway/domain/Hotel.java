package com.synergisticit.searchgateway.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="hotels")
public class Hotel {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hotelId;
	private String hotelName;
	private String address;	
	private String city;	
	private String state;
	private int starRating;
	private double averagePrice;
	@ManyToMany
	private Set<Amenities> amenities = new HashSet<>();
	private double discount;
	private String description;
	private String email;
	private String mobile;
	private String imageURL;	
	private int timesBooked;
	
	@OneToMany
	private Set<HotelRoom> hotelRooms = new HashSet<>();
	
	@Transient
	private Set<String> hotelAmenityNames = new HashSet<>();
	
//	public Hotel() {
//		super();
//	}
//
//	public Hotel(int hotelId, String hotelName, String address, String city, String state, int starRating,
//			double averagePrice, Set<Amenities> amenities, double discount, String description, String email,
//			String mobile, String imageURL, int timesBooked, Set<HotelRoom> hotelRooms, Set<String> hotelAmenityNames) {
//		super();
//		this.hotelId = hotelId;
//		this.hotelName = hotelName;
//		this.address = address;
//		this.city = city;
//		this.state = state;
//		this.starRating = starRating;
//		this.averagePrice = averagePrice;
//		this.amenities = amenities;
//		this.discount = discount;
//		this.description = description;
//		this.email = email;
//		this.mobile = mobile;
//		this.imageURL = imageURL;
//		this.timesBooked = timesBooked;
//		this.hotelRooms = hotelRooms;
//		this.hotelAmenityNames = hotelAmenityNames;
//	}

}
