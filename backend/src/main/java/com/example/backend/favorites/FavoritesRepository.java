package com.example.backend.favorites;

import com.example.backend.hotel.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    boolean existsByUser_IdAndHotel_Id(Long userId, Long hotelId);
}

