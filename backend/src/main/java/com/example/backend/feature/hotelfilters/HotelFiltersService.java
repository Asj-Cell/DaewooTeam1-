package com.example.backend.feature.hotelfilters;

import com.example.backend.hotel.entity.Hotel;
import com.example.backend.feature.hotelfilters.dto.HotelDto;
import com.example.backend.feature.hotelfilters.dto.HotelFilterRequestDto;
import com.example.backend.hotel.HotelRepository;
import com.example.backend.hotel.entity.Room;
import com.example.backend.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelFiltersService {

        private final HotelRepository hotelRepository;
        private final ReviewRepository reviewRepository; // 추가

        public Page<HotelDto> filterHotels(HotelFilterRequestDto request, Pageable pageable) {
            Specification<Hotel> spec = HotelSpecifications.withFilters(request);

            Page<Hotel> hotelPage = hotelRepository.findAll(spec, pageable);

            return hotelPage.map(h -> {
                Double totalRating = reviewRepository.findTotalRatingByHotelId(h.getId());
                long reviewCount = reviewRepository.countByHotelId(h.getId());
                double avgRating = (totalRating != null && reviewCount > 0) ? totalRating / reviewCount : 0.0;

                return new HotelDto(
                        h.getId(),
                        h.getName(),
                        h.getCity().getCityName(),
                        h.getGrade(),
                        countAmenities(h),
                        getLowestAvailablePrice(h, request),
                        avgRating,
                        getRepresentativeImage(h)
                );
            });
        }

    // 호텔이 가지고 있는 무료서비스 + 편의시설 카운트
    private int countAmenities(Hotel h) {
        int count = 0;
        // Freebies
        if (h.getFreebies().isBreakfastIncluded()) count++;
        if (h.getFreebies().isFreeParking()) count++;
        if (h.getFreebies().isFreeWifi()) count++;
        if (h.getFreebies().isAirportShuttlebus()) count++;
        if (h.getFreebies().isFreeCancellation()) count++;

        // Amenities
        if (h.getAmenities().isFrontDesk24()) count++;
        if (h.getAmenities().isAirConditioner()) count++;
        if (h.getAmenities().isFitnessCenter()) count++;
        if (h.getAmenities().isOutdoorPool() || h.getAmenities().isIndoorPool()) count++; // 수영장 합치기
        if (h.getAmenities().isSpaWellnessCenter()) count++;
        if (h.getAmenities().isRestaurant()) count++;
        if (h.getAmenities().isRoomservice()) count++;
        if (h.getAmenities().isBarLounge()) count++;
        if (h.getAmenities().isTeaCoffeeMachine()) count++;

        return count;
    }

    private boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        return room.getReservations().stream().noneMatch(reservation ->
                reservation.getCheckinDate().isBefore(checkOut) &&
                        reservation.getCheckoutDate().isAfter(checkIn)
        );
    }

    private BigDecimal getLowestAvailablePrice(Hotel h, HotelFilterRequestDto request) {
        return h.getRooms().stream()
                .filter(r -> isRoomAvailable(r, request.getCheckInDate(), request.getCheckOutDate()))
                .filter(r -> request.getMinAvailableRooms() == null || r.getMaxGuests() >= request.getMinAvailableRooms())
                .map(Room::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }


    private String getRepresentativeImage(Hotel h) {
        // 임시 이미지
        return "https://example.com/default-image.jpg";
    }
}

