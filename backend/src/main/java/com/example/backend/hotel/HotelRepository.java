// backend/src/main/java/com/example/backend/hotel/HotelRepository.java

package com.example.backend.hotel;

import com.example.backend.hotel.dto.HotelSearchResponseDto;
import com.example.backend.hotel.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT new com.example.backend.hotel.dto.HotelSearchResponseDto(" +
            "h.id, h.name, h.address, h.grade, hi.imageUrl, MIN(r.price)) " +
            "FROM Hotel h " +
            "JOIN h.rooms r " +
            "LEFT JOIN h.images hi ON hi.sequence = 1 " + // 대표 이미지(sequence=1)만 JOIN
            "WHERE (h.name LIKE %:destination% OR h.address LIKE %:destination% OR h.city.cityName LIKE %:destination%) " +
            "AND r.maxGuests >= :guestCount " +
            "AND r.id NOT IN (" +
            "  SELECT res.room.id FROM Reservation res " +
            "  WHERE res.checkoutDate > :checkInDate AND res.checkinDate < :checkOutDate" +
            ") " +
            "GROUP BY h.id, hi.imageUrl", // imageUrl도 GROUP BY에 추가
            countQuery = "SELECT count(h) FROM Hotel h WHERE (h.name LIKE %:destination% OR h.address LIKE %:destination% OR h.city.cityName LIKE %:destination%)")
            Page<HotelSearchResponseDto> searchHotels(
            @Param("destination") String destination,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("guestCount") Integer guestCount,
            Pageable pageable
    );

    @Query("SELECT new com.example.backend.hotel.dto.HotelSearchResponseDto(" +
            "h.id, h.name, h.address, h.grade, hi.imageUrl, MIN(r.price)) " +
            "FROM Hotel h " +
            "JOIN h.rooms r " +
            "LEFT JOIN h.images hi ON hi.sequence = 1 " +
            "GROUP BY h.id, hi.imageUrl " +
            "ORDER BY h.grade DESC, MIN(r.price) ASC")
    Page<HotelSearchResponseDto> findRecommended(Pageable pageable);
}