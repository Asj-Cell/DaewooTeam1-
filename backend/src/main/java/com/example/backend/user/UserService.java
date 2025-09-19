package com.example.backend.user;

import com.example.backend.common.util.FileStorageService;
import com.example.backend.payment.PaymentRepository;
import com.example.backend.user.dto.*;
import com.example.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public String updateUserProfileImage(Long userId, MultipartFile imageFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        // 파일을 서버에 저장하고 웹 경로를 받아옴
        String imageUrl = fileStorageService.storeFile(imageFile);

        // 사용자의 프로필 이미지 URL 업데이트
        user.setImageUrl(imageUrl);
        userRepository.save(user); // 변경 사항 저장

        return imageUrl; // 새로 업데이트된 이미지 URL 반환
    }

    @Transactional
    public String updateUserBackgroundImage(Long userId, MultipartFile imageFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        // 파일을 서버에 저장하고 웹 경로를 받아옴
        String backgroundImageUrl = fileStorageService.storeFile(imageFile);

        // 사용자의 배경 이미지 URL 업데이트
        user.setBackGroundImageUrl(backgroundImageUrl);
        userRepository.save(user); // 변경 사항 저장

        return backgroundImageUrl; // 새로 업데이트된 이미지 URL 반환
    }

    // (선택사항) 프로필 텍스트 정보 업데이트 로직
    @Transactional
    public UserProfileAllResponseDto updateUserProfileInfo(Long userId, UserProfileRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        user.setUserName(requestDto.getUserName());
        user.setAddress(requestDto.getAddress());
        // 필요에 따라 다른 필드들도 업데이트

        User updatedUser = userRepository.save(user);
        return new UserProfileAllResponseDto(updatedUser);
    }




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
    public List<ReservationDetailDto> getUserReservations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        List<ReservationDetailDto> reservationDetails = user.getReservations().stream()
                .map(reservation -> new ReservationDetailDto(reservation)) // Reservation -> ReservationDetailDto 변환
                .toList();

        return reservationDetails; // Wrapper DTO로 감싸서 반환
    }

    @Transactional(readOnly = true)
    public List<UserProfilePaymentMethodDto> getUserPaymentMethods(Long userId) {
        // userId로 모든 Payment 엔티티를 조회
        return paymentRepository.findAllByUserId(userId).stream()
                .map(UserProfilePaymentMethodDto::new) // Payment -> DTO 변환
                .toList();
    }
}