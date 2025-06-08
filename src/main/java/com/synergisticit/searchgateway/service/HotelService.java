package com.synergisticit.searchgateway.service;


import com.synergisticit.searchgateway.domain.Amenities;
import com.synergisticit.searchgateway.domain.Hotel;
import com.synergisticit.searchgateway.repository.AmenitiesRepository;
import com.synergisticit.searchgateway.repository.HotelRepository;
import com.synergisticit.searchgateway.repository.HotelRoomRepository;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private AmenitiesRepository amenitiesRepository;
    @Autowired
    private HotelRoomRepository hotelRoomRepository;
    @Autowired
    private VectorStore vectorStore;
    
    public Hotel saveHotel(Hotel hotel) {
        hotel.setStarRating(new Random().nextInt(5) + 1);
        hotel.setAveragePrice(new Random().nextFloat(400) + 200);
        Set<Amenities> amenities = new HashSet<>();
        // Assign all amenities to the hotel. (change the index if you need to insert different amenities)
        List<Amenities> data =amenitiesRepository.findAll();
        for (int i = 0; i < 5; i++) {
            amenities.add(data.get(i));
        }
        hotel.setAmenities(amenities);
        
        hotel.setDiscount(new Random().nextFloat());
        hotel.setDescription("This is a description");
        hotel.setEmail("test@gmail.com");
        hotel.setMobile("1234567890");
        
        // I will manipulate the data in the database because one hotel room only can
        // be assigned to one hotel. For testing purposes, I will split them into different hotels.
//        Set<HotelRoom> hotelRooms = new HashSet<>();
        // Assign all hotelRooms to the hotel.
//        hotelRoomRepository.findAll().forEach(hotelRooms::add);
//        hotel.setHotelRooms(hotelRooms);
        
        hotel.setTimesBooked(new Random().nextInt(101));
        Hotel savedHotel = hotelRepository.save(hotel);
        var document = new Document("id: %s, name: %s, address: %s, city: %s, State: %s, rate: %s, timesbooked: %s, amenities: %s"
                .formatted(savedHotel.getHotelId(), savedHotel.getHotelName(), savedHotel.getAddress(),
                        savedHotel.getCity(), savedHotel.getState(), savedHotel.getStarRating(),
                        savedHotel.getTimesBooked(), savedHotel.getHotelAmenityNames().toString()));
        vectorStore.add(List.of(document));
        return savedHotel;
    }
    
    public List<Hotel> searchHotels(String searchLocation, int noRooms) {
        // Create the Specification using the searchHotels method (searchName can include hotelName, city, state, or address)
        Specification<Hotel> specification = HotelSearchSpecifications.searchHotels(searchLocation, noRooms);
        return hotelRepository.findAll(specification);
    }
}
