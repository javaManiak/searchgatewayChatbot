package com.synergisticit.searchgateway.controller;


import com.synergisticit.searchgateway.domain.RoomType;
import com.synergisticit.searchgateway.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomTypeController {
    
    @Autowired
    private RoomTypeService roomTypeService;
    
    @PostMapping("/saveRoomType")
    public RoomType saveRoomType(@RequestBody RoomType roomType) {
        return roomTypeService.saveRoomType(roomType);
    }
    
}
