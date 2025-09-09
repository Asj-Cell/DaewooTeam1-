package com.example.backend.feature.hotelfilters.mapper;

import com.example.backend.feature.hotelfilters.dto.HotelDto;
import com.example.backend.feature.hotelfilters.dto.HotelFilterRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface HotelfiltersMapper {
    Long countBySearch(HotelFilterRequestDto filterRequestDto, Long foodCategoryId);
    List<HotelDto> findBySearch(HotelFilterRequestDto filterRequestDto, Pageable pageable);
}