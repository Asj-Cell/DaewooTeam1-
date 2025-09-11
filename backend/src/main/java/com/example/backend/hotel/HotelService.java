//package com.example.backend.hotel;
//
//import com.example.backend.hotel.dto.HotelSearchRequestDto;
//import com.example.backend.hotel.dto.HotelSearchResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class HotelService {
//
//    private final HotelRepository hotelRepository;
//
//    public Page<HotelSearchResponseDto> searchHotels(HotelSearchRequestDto requestDto, Pageable pageable) {
//        String destination = requestDto.getDestination();
//
//        // 목적지 검색어가 없으면 바로 평점순 추천
//        if (destination == null || destination.trim().isEmpty()) {
//            return getRecommendedHotels(pageable);
//        }
//
//        // 목적지 검색어가 있으면 검색 실행
//        Page<HotelSearchResponseDto> results = hotelRepository.searchHotels(
//                destination.trim(),
//                requestDto.getCheckInDate(),
//                requestDto.getCheckOutDate(),
//                requestDto.getGuestCount(),
//                pageable
//        );
//
//        // 검색 결과가 없으면 평점순 추천
//        if (results.isEmpty()) {
//            return getRecommendedHotels(pageable);
//        }
//
//        return results;
//    }
//
//    private Page<HotelSearchResponseDto> getRecommendedHotels(Pageable pageable) {
//        return hotelRepository.findRecommended(pageable);
//    }
//}