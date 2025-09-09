package com.example.backend.feature.hotelfilters;

import com.example.backend.domain.Hotel;
import com.example.backend.feature.hotelfilters.dto.HotelDto;
import com.example.backend.feature.hotelfilters.dto.HotelFilterRequestDto;
import com.example.backend.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelFiltersService {

    private final HotelRepository hotelRepository;

    public List<HotelDto> filterHotels(HotelFilterRequestDto request){
        return hotelRepository.findAll().stream()

                .filter(h -> request.getBreakfastIncluded() == null || h.getFreebies().isBreakfastIncluded())
                .filter(h -> request.getFreeParking() == null || h.getFreebies().isFreeParking())
                .filter(h -> request.getFreeWifi() == null || h.getFreebies().isFreeWifi())
                .filter(h -> request.getAirportShuttlebus() == null || h.getFreebies().isAirportShuttlebus())
                .filter(h -> request.getFreeCancellation() == null || h.getFreebies().isFreeCancellation())

                .filter(h -> request.getFrontDesk24() == null || h.getAmenities().isFrontDesk24())
                .filter(h -> request.getAirConditioner() == null || h.getAmenities().isAirConditioner())
                .filter(h -> request.getFitnessCenter() == null || h.getAmenities().isFitnessCenter())
                // 수영장: 야외 또는 실내 풀 중 하나라도 있어야 함
                .filter(h -> request.getPool() == null || (h.getAmenities().isOutdoorPool() || h.getAmenities().isIndoorPool()))

                .filter(h -> request.getMinGrade() == null || h.getGrade() >= request.getMinGrade())

                .filter(h -> {
                    BigDecimal lowestPrice = h.getRooms().stream()
                            .map(r -> r.getPrice())
                            .min(BigDecimal::compareTo)
                            .orElse(BigDecimal.ZERO);
                    boolean minCheck = request.getMinprice() == null || lowestPrice.compareTo(request.getMinprice()) >= 0;
                    boolean maxCheck = request.getMaxprice() == null || lowestPrice.compareTo(request.getMaxprice()) <= 0;
                    return minCheck && maxCheck;
                })
                .map(h -> new HotelDto(
                        h.getId(),
                        h.getName(),
                        h.getCity().getCityName(),
                        h.getGrade(),
//                        countAmenities(h),
                        getLowestPrice(h),
//                         호텔 이미지를 출력해야함
                        getRepresentativeImage(h)
                ))
                .collect(Collectors.toList());
    }
    //호텔이 가지고 있는 무료서비스,편의시설의 갯수를 카운트
//    private int countAmenities(Hotel h) {
//        int count = 0;
//
//        if (h.getFreebies().isBreakfastIncluded()) count++;
//        if (h.getFreebies().isFreeParking()) count++;
//        if (h.getFreebies().isFreeWifi()) count++;
//        if (h.getFreebies().isAirportShuttlebus()) count++;
//        if (h.getFreebies().isFreeCancellation()) count++;
//
//        if (h.getAmenities().isFrontDesk24()) count++;
//        if (h.getAmenities().isAirConditioner()) count++;
//        if (h.getAmenities().isFitnessCenter()) count++;
//        //호텔에 내부든 외부든 수영장이 있다면
//        if (h.getAmenities().isOutdoorPool() || h.getAmenities().isIndoorPool()) count++; // 풀장은 하나로 합치기
//        if (h.getAmenities().isSpaWellnessCenter()) count++;
//        if (h.getAmenities().isRestaurant()) count++;
//        if (h.getAmenities().isRoomservice()) count++;
//        if (h.getAmenities().isBarLounge()) count++;
//        if (h.getAmenities().isTeaCoffeeMachine()) count++;
//
//        return count;
//    }

    private BigDecimal getLowestPrice(Hotel h) {
        return h.getRooms().stream()
                .map(r -> r.getPrice())
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    private String getRepresentativeImage(Hotel h) {
        return "https://example.com/default-image.jpg";
    }
}
