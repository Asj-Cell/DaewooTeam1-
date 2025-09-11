package com.example.backend.repository;

import com.example.backend.hotel.entity.Freebies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreebiesRepository extends JpaRepository<Freebies, Long> {
}
