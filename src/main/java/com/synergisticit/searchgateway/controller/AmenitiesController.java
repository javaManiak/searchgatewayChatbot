package com.synergisticit.searchgateway.controller;


import com.synergisticit.searchgateway.domain.Amenities;
import com.synergisticit.searchgateway.service.AmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmenitiesController {
    
    @Autowired
    private AmenitiesService amenitiesService;
    
    @PostMapping("/saveAmenity")
    public Amenities saveAmenity(@RequestBody Amenities amenity) {
        return amenitiesService.saveAmenity(amenity);
    }
}
