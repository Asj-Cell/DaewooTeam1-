package com.example.backend.city.service;

import org.springframework.stereotype.Service;

import com.example.backend.city.repository.CityRepostiory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
  
  private final CityRepostiory cityRepostiory;
  
}
