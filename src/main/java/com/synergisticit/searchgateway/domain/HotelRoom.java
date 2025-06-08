package com.synergisticit.searchgateway.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name="hotel_rooms")
public class HotelRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hotelRoomId; //PK	
	@ManyToOne
	private RoomType type;
	@ManyToMany
	private Set<Amenities> amenities;
	private int noRooms;
	private float price;
	private float discount;
	private String description;
	private String policies;
		
	@Transient
	private String hotelName;
	
	@Transient
	private String roomType;
	
	@Transient
	private Set<String> hotelRoomAmenityNames = new HashSet<>();

    public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

    public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

    public void setHotelRoomId(int hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}

    public void setType(RoomType type) {
		this.type = type;
	}

    public void setHotelRoomAmenities(Set<Amenities> hotelRoomAmenities) {
		this.amenities = hotelRoomAmenities;
	}

    public void setHotelRoomAmenityNames(Set<String> hotelRoomAmenityNames) {
		this.hotelRoomAmenityNames = hotelRoomAmenityNames;
	}

    public void setNoRooms(int noRooms) {
		this.noRooms = noRooms;
	}

    public void setPrice(float price) {
		this.price = price;
	}

    public void setDiscount(float discount) {
		this.discount = discount;
	}

    public void setDescription(String description) {
		this.description = description;
	}

    public void setPolicies(String policies) {
		this.policies = policies;
	}
	
	
}
