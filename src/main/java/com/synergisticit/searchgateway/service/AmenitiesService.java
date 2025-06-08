package com.synergisticit.searchgateway.service;

import com.synergisticit.searchgateway.domain.Amenities;
import com.synergisticit.searchgateway.repository.AmenitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenitiesService {
    @Autowired
    private AmenitiesRepository amenitiesRepository;
    
    public Amenities saveAmenity(Amenities amenity) {
       return amenitiesRepository.save(amenity);
    }
}
