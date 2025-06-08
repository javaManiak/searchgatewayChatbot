package com.synergisticit.searchgateway.controller;



import com.synergisticit.searchgateway.domain.Hotel;
import com.synergisticit.searchgateway.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    
    @Autowired
    private HotelService hotelService;
    
    @PostMapping("/createHotel")
    public Hotel saveHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }
    
    @GetMapping("/searchHotels")
    public List<Hotel> searchHotel(
            @RequestParam String searchLocation,
            @RequestParam int noRooms) {
        return hotelService.searchHotels(searchLocation, noRooms);
    }
}
