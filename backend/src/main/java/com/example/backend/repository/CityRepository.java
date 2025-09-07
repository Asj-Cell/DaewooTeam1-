package com.example.backend.repository;

import com.example.backend.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CityRepository  extends JpaRepository<City, Long> {
}
