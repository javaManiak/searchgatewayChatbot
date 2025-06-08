package com.synergisticit.searchgateway.service;

import com.synergisticit.searchgateway.domain.RoomType;
import com.synergisticit.searchgateway.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    
    public RoomType saveRoomType(RoomType roomType) {
       return roomTypeRepository.save(roomType);
    }
}
