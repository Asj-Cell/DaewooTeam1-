package com.example.backend.feature.hotelfilters.detail;

import com.example.backend.favorites.FavoritesRepository;
import com.example.backend.hotel.HotelRepository;
import com.example.backend.hotel.entity.Hotel;
import com.example.backend.hotel.entity.HotelImage;
import com.example.backend.review.ReviewRepository;
import com.example.backend.room.RoomService;
import com.example.backend.room.dto.RoomDto;
import com.example.backend.room.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelDetailService {

    private final HotelRepository hotelRepository;
    private final RoomService roomService;
    private final ReviewRepository reviewRepository;
    private final FavoritesRepository favoritesRepository;

    public HotelDetailDto getHotelDetail(Long hotelId, Long loginUserId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + hotelId));

        // 1. 편의시설 리스트 변환
        List<String> amenities = getAmenitiesList(hotel);

        // 2. 룸 리스트 변환
        List<RoomDto> rooms = roomService.getRoomsByHotelId(hotelId);

        // 3. 리뷰 계산
        Double totalRating = reviewRepository.findTotalRatingByHotelId(hotelId);
        long reviewCount = reviewRepository.countByHotelId(hotelId);
        double avgRating = (totalRating != null && reviewCount > 0) ? totalRating / reviewCount : 0.0;

        // 4. 찜 여부 (옵션)
        boolean isFavorite = (loginUserId != null) &&
                favoritesRepository.existsByUser_IdAndHotel_Id(loginUserId, hotelId);

        List<String> roomImageUrls = hotel.getRooms().stream()
                .flatMap(room -> room.getImages().stream())
                .map(img -> img.getImageUrl())
                .toList();

        List<String> hotelImageUrls = hotel.getImages().stream()
                .map(HotelImage::getImageUrl)
                .toList();

        // 6. HotelDetailDto 생성
        HotelDetailDto detailDto = new HotelDetailDto(
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getGrade(),
                countAmenities(hotel), // 편의시설 + 무료서비스 개수
                getLowestRoomPrice(hotel), // 최저 객실 가격
                avgRating,
                hotelImageUrls,
                isFavorite,
                reviewCount,
                amenities,
                rooms,
                hotel.getOverview(),
                roomImageUrls
        );

        return detailDto;
    }

    private List<String> getAmenitiesList(Hotel h) {
        List<String> list = new ArrayList<>();
        if (h.getAmenities().isFrontDesk24()) list.add("24시간 프론트");
        if (h.getAmenities().isAirConditioner()) list.add("에어컨");
        if (h.getAmenities().isFitnessCenter()) list.add("헬스장");
        if (h.getAmenities().isOutdoorPool()) list.add("야외 수영장");
        if (h.getAmenities().isIndoorPool()) list.add("실내 수영장");
        if (h.getAmenities().isSpaWellnessCenter()) list.add("스파");
        if (h.getAmenities().isRestaurant()) list.add("레스토랑");
        if (h.getAmenities().isRoomservice()) list.add("룸서비스");
        if (h.getAmenities().isBarLounge()) list.add("바/라운지");
        if (h.getAmenities().isTeaCoffeeMachine()) list.add("차/커피");

        if (h.getFreebies().isBreakfastIncluded()) list.add("조식 포함");
        if (h.getFreebies().isFreeParking()) list.add("무료 주차");
        if (h.getFreebies().isFreeWifi()) list.add("무료 와이파이");
        if (h.getFreebies().isAirportShuttlebus()) list.add("공항 셔틀버스");
        if (h.getFreebies().isFreeCancellation()) list.add("무료 취소");
        return list;
    }

    private BigDecimal getLowestRoomPrice(Hotel hotel) {
        return hotel.getRooms().stream()
                .map(Room::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    private int countAmenities(Hotel h) {
        int count = 0;
        if (h.getFreebies().isBreakfastIncluded()) count++;
        if (h.getFreebies().isFreeParking()) count++;
        if (h.getFreebies().isFreeWifi()) count++;
        if (h.getFreebies().isAirportShuttlebus()) count++;
        if (h.getFreebies().isFreeCancellation()) count++;
        // amenities
        if (h.getAmenities().isFrontDesk24()) count++;
        if (h.getAmenities().isAirConditioner()) count++;
        if (h.getAmenities().isFitnessCenter()) count++;
        if (h.getAmenities().isOutdoorPool() || h.getAmenities().isIndoorPool()) count++;
        if (h.getAmenities().isSpaWellnessCenter()) count++;
        if (h.getAmenities().isRestaurant()) count++;
        if (h.getAmenities().isRoomservice()) count++;
        if (h.getAmenities().isBarLounge()) count++;
        if (h.getAmenities().isTeaCoffeeMachine()) count++;
        return count;
    }
}

