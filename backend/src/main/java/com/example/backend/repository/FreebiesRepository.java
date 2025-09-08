package com.example.backend.repository;

import com.example.backend.domain.Freebies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreebiesRepository extends JpaRepository<Freebies, Long> {
}
