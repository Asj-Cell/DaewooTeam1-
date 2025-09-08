package com.example.backend.feature.hotelfilters;

import com.example.backend.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelFiltersService {

    private final HotelRepository hotelRepository;
}
