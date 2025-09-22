package com.example.backend.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.hotel.HotelRepository;
import com.example.backend.hotel.entity.Hotel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {
  
  private final HotelRepository hotelRepository;  

  public List<Hotel> getAllHotels() {
      return hotelRepository.findAll();
  }

}
