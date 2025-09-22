package com.example.backend.hotel.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.hotel.entity.Hotel;
import com.example.backend.hotel.service.HotelService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {
  
  private final HotelService hotelService;  
  
  @GetMapping("/hotels")
  public List<Hotel> getMethodName() {
      return hotelService.getAllHotels();
  }
  
  
}
