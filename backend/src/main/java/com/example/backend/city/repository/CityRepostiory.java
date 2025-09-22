package com.example.backend.city.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.city.entity.City;

public interface CityRepostiory extends JpaRepository<City, Long> { 
  
}
