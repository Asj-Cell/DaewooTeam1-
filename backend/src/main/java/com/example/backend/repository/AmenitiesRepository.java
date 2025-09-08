package com.example.backend.repository;

import com.example.backend.domain.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
}
