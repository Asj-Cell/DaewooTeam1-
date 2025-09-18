package com.example.backend.feature.hotelfilters;

import com.example.backend.favorites.FavoritesRepository;
import com.example.backend.hotel.entity.Hotel;
import com.example.backend.feature.hotelfilters.dto.HotelDto;
import com.example.backend.feature.hotelfilters.dto.HotelFilterRequestDto;
import com.example.backend.hotel.HotelRepository;
import com.example.backend.room.entity.Room;
import com.example.backend.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelFiltersService {

    private final HotelRepository hotelRepository;
    private final ReviewRepository reviewRepository;
    private final FavoritesRepository favoritesRepository;

    public Page<HotelDto> filterHotels(HotelFilterRequestDto request, Pageable pageable) {
        // 1. Specification으로 DB에서 필터 적용
        Specification<Hotel> spec = HotelSpecifications.withFilters(request);

        // 2. DB 조회 + 페이징 처리
        Page<Hotel> hotelPage = hotelRepository.findAll(spec, pageable);

        Long loginUserId;
        if (request.getLoginUser() != null) {
            loginUserId = request.getLoginUser().getUserId();
        } else {
            loginUserId = null;
        }
        // 3. DTO 변환 + 정렬
        List<HotelDto> sortedDtos = hotelPage.stream()
                .map(h -> {
                    // 리뷰 기반 평점 계산
                    Double totalRating = reviewRepository.findTotalRatingByHotelId(h.getId());
                    long reviewCount = reviewRepository.countByHotelId(h.getId());
                    double avgRating = (totalRating != null && reviewCount > 0) ? totalRating / reviewCount : 0.0;

                    boolean isFavorite = false;
                    if (request.getLoginUser() != null && request.getLoginUser().getUserId() != null) {
                        isFavorite = favoritesRepository.existsByUser_IdAndHotel_Id(
                                request.getLoginUser().getUserId(), h.getId()
                        );
                    }


                    return new HotelDto(
                            h.getId(),
                            h.getName(),
                            h.getAddress(),
                            h.getGrade(),
                            countAmenities(h),
                            getLowestAvailablePrice(h, request),
                            avgRating,
                            getRepresentativeImage(h),
                            isFavorite
                    );
                })

                .filter(dto -> request.getMinAvgRating() == null || dto.getRating() >= request.getMinAvgRating())

                .sorted((h1, h2) -> {
                    // 정렬 기준에 따라 정렬
                    if ("rating".equalsIgnoreCase(request.getSortBy())) {
                        return Double.compare(h2.getRating(), h1.getRating()); // 평점 내림차순
                    } else if ("priceAsc".equalsIgnoreCase(request.getSortBy())) {
                        return h1.getPrice().compareTo(h2.getPrice());       // 가격 오름차순
                    } else if ("priceDesc".equalsIgnoreCase(request.getSortBy())) {
                        return h2.getPrice().compareTo(h1.getPrice());       // 가격 내림차순
                    } else {
                        return 0; // 기본: DB 순서, 아마 아이디 순으로 조회될겁니다
                    }
                })
                .collect(Collectors.toList());

        // 4. Stream 정렬 후 새 Page 객체 생성
        return new PageImpl<>(sortedDtos, pageable, hotelPage.getTotalElements());
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
        if (checkIn == null || checkOut == null) return true;
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

