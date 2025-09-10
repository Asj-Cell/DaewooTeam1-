package com.example.backend.feature.hotelfilters;

import com.example.backend.domain.Hotel;
import com.example.backend.feature.hotelfilters.dto.HotelFilterRequestDto;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class HotelSpecifications {

    public static Specification<Hotel> withFilters(HotelFilterRequestDto request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Freebies
            if (Boolean.TRUE.equals(request.getBreakfastIncluded())) {
                predicates.add(cb.isTrue(root.get("freebies").get("breakfastIncluded")));
            }
            if (Boolean.TRUE.equals(request.getFreeParking())) {
                predicates.add(cb.isTrue(root.get("freebies").get("freeParking")));
            }
            if (Boolean.TRUE.equals(request.getFreeWifi())) {
                predicates.add(cb.isTrue(root.get("freebies").get("freeWifi")));
            }
            if (Boolean.TRUE.equals(request.getAirportShuttlebus())) {
                predicates.add(cb.isTrue(root.get("freebies").get("airportShuttlebus")));
            }
            if (Boolean.TRUE.equals(request.getFreeCancellation())) {
                predicates.add(cb.isTrue(root.get("freebies").get("freeCancellation")));
            }

            // Amenities
            if (Boolean.TRUE.equals(request.getFrontDesk24())) {
                predicates.add(cb.isTrue(root.get("amenities").get("frontDesk24")));
            }
            if (Boolean.TRUE.equals(request.getAirConditioner())) {
                predicates.add(cb.isTrue(root.get("amenities").get("airConditioner")));
            }
            if (Boolean.TRUE.equals(request.getFitnessCenter())) {
                predicates.add(cb.isTrue(root.get("amenities").get("fitnessCenter")));
            }
            // 수영장: 실내/외 풀 중 하나라도 있으면 ok
            if (Boolean.TRUE.equals(request.getPool())) {
                predicates.add(cb.or(
                        cb.isTrue(root.get("amenities").get("outdoorPool")),
                        cb.isTrue(root.get("amenities").get("indoorPool"))
                ));
            }

            // Grade
            if (request.getMinGrade() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("grade"), request.getMinGrade()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
