package com.synergisticit.searchgateway.service;

import com.synergisticit.searchgateway.domain.Amenities;
import com.synergisticit.searchgateway.domain.HotelRoom;
import com.synergisticit.searchgateway.domain.RoomType;
import com.synergisticit.searchgateway.repository.AmenitiesRepository;
import com.synergisticit.searchgateway.repository.HotelRoomRepository;
import com.synergisticit.searchgateway.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelRoomService {
    @Autowired
    private HotelRoomRepository hotelRoomRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private AmenitiesRepository amenitiesRepository;
    
    public HotelRoom saveHotelRoom() {
        HotelRoom hotelRoom = new HotelRoom();
        // Randomly assign a room type to the hotel room
        Optional<RoomType> roomType = roomTypeRepository.findById(new Random().nextInt(5) + 1);
        if(roomType.isPresent()) {
            hotelRoom.setType(roomType.get());
        }else {
            return null;
        }
        // We have 14 amenities in the database now
        Iterable<Integer> amenitiesIds = new Random().ints(5, 1, 14).boxed()    // Convert each int to Integer (Stream<Integer>)
                                                 .collect(Collectors.toList()); // Collect into a List;
        Set<Amenities> amenities = new HashSet<>();
        // Randomly assign amenities to the hotel room
        amenitiesRepository.findAllById(amenitiesIds).forEach(amenities::add);
        hotelRoom.setHotelRoomAmenities(amenities);
        
        /** assume only 10 rooms per hotelRoom in maximum */
        hotelRoom.setNoRooms(new Random().nextInt(10));
        hotelRoom.setPrice(100 + (400 * new Random().nextFloat()));
        hotelRoom.setDiscount(new Random().nextFloat());
        hotelRoom.setDescription("This is a description");
        hotelRoom.setPolicies("These are the policies");
       
       return hotelRoomRepository.save(hotelRoom);
    }
}
