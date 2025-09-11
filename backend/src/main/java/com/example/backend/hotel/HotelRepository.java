// backend/src/main/java/com/example/backend/hotel/HotelRepository.java

package com.example.backend.hotel;


import com.example.backend.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
}