package com.synergisticit.searchgateway.service;

import com.synergisticit.searchgateway.domain.Hotel;
import com.synergisticit.searchgateway.domain.HotelRoom;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class HotelSearchSpecifications {
    // Specification to filter by Hotel/City/State/Address (LIKE search)
    public static Specification<Hotel> nameLike(String name) {
        return (root, query, builder) -> {
            if (name != null && !name.isEmpty()) {;
                return builder.like(builder.lower(root.get("hotelName")), "%" + name.toLowerCase() + "%"); // LIKE %name%
            }
            return null;
        };
    }
    // Specification to filter by city (LIKE search)
    public static Specification<Hotel> cityLike(String city) {
        return (root, query, builder) -> {
            if (city != null && !city.isEmpty()) {
                return builder.like(builder.lower(root.get("city")), "%" + city.toLowerCase() + "%"); // LIKE %city%
            }
            return null;
        };
    }
    // Specification to filter by city (LIKE search)
    public static Specification<Hotel> stateLike(String state) {
        return (root, query, builder) -> {
            if (state != null && !state.isEmpty()) {
                return builder.like(builder.lower(root.get("state")), "%" + state.toLowerCase() + "%"); // LIKE %city%
            }
            return null;
        };
    }
    // Specification to filter by city (LIKE search)
    public static Specification<Hotel> addressLike(String address) {
        return (root, query, builder) -> {
            if (address != null && !address.isEmpty()) {
                return builder.like(builder.lower(root.get("address")), "%" + address.toLowerCase() + "%"); // LIKE %city%
            }
            return null;
        };
    }
    // Specification the search for room numbers
    public static Specification<Hotel> roomNumsCheck(int noRooms) {
        return (root, query, builder) -> {
            // Join Hotel and HotelRoom entities (inner join)
            Join<Hotel, HotelRoom> hotelRoomJoin = root.join("hotelRooms", JoinType.INNER);
            // Create a predicate to filter by the number of rooms
            Predicate roomNumPredicate = builder.greaterThanOrEqualTo(hotelRoomJoin.get("noRooms"), noRooms);
            return roomNumPredicate;
        };
    }
    // Combine all the specifications with AND logic (name LIKE OR city LIKE, etc.)
    public static Specification<Hotel> searchHotels(String name, int noRooms) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction(); // Start with "true" condition
//            System.out.println("name: " + name);
            if (name != null && !name.isEmpty()) {
                Predicate localPredicate = builder.or(nameLike(name).toPredicate(root, query, builder),
                        cityLike(name).toPredicate(root, query, builder),
                        stateLike(name).toPredicate(root, query, builder),
                        addressLike(name).toPredicate(root, query, builder));
                predicate = builder.and(predicate, localPredicate);
            }
//            System.out.println("noRooms: " + noRooms);
            if (noRooms >=0) {
                predicate = builder.and(predicate, roomNumsCheck(noRooms).toPredicate(root, query, builder));
            }
            return predicate;
        };
    }
}
