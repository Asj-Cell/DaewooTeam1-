package com.example.backend.user;

import com.example.backend.user.dto.ReservationDetailDto;
import com.example.backend.user.dto.UserProfileAllResponseDto; // 추가
import com.example.backend.user.dto.UserProfileResponseDto;
import com.example.backend.user.dto.UserReservationResponseDto;
import com.example.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserProfileAllResponseDto getUserProfileAll(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new UserProfileAllResponseDto(user);
    }
    @Transactional(readOnly = true)
    public UserProfileResponseDto getUserProfile(Long userId) {
        // ID로 사용자를 찾고, 없으면 예외를 발생시킵니다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // User 엔티티를 UserProfileResponseDto로 변환하여 반환합니다.
        return new UserProfileResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserReservationResponseDto getUserReservations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        List<ReservationDetailDto> reservationDetails = user.getReservations().stream()
                .map(reservation -> new ReservationDetailDto(reservation)) // Reservation -> ReservationDetailDto 변환
                .toList();

        return new UserReservationResponseDto(reservationDetails); // Wrapper DTO로 감싸서 반환
    }
}