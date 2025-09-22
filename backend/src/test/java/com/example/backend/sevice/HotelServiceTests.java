package com.example.backend.sevice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.hotel.HotelRepository;
import com.example.backend.hotel.entity.Hotel;
import com.example.backend.hotel.service.HotelService;

@SpringBootTest
public class HotelServiceTests {
  
  @Autowired
  private HotelService hotelService;

  @Autowired
  private HotelRepository hotelRepository;

  @Test
  public void insertHotel() {
    Hotel h = Hotel.builder()
      .name("Test Hotel")
      .grade(5)
      .overview("This is a test hotel.")
      .latitude(37.5665)
      .longitude(126.9780)
      .address("123 Test St, Test City")
      .checkinTime(java.time.LocalTime.of(14, 0))
      .checkoutTime(java.time.LocalTime.of(12, 0))
      .build();   

    hotelRepository.save(h);    
  } 

  @Test
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels(); 
  }
}
