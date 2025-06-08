package com.synergisticit.searchgateway.repository;

import com.synergisticit.searchgateway.domain.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Integer> {
}
