package com.synergisticit.searchgateway.controller;


import com.synergisticit.searchgateway.domain.HotelRoom;
import com.synergisticit.searchgateway.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelRoomController {
    @Autowired
    private HotelRoomService hotelRoomService;
    
    @PostMapping("/createHotelRoom")
    public HotelRoom saveHotelRoom() {
        return hotelRoomService.saveHotelRoom();
        
    }
}
