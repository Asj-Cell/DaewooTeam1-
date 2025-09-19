package com.example.backend.user;

import com.example.backend.common.util.ApiResponse;
import com.example.backend.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Tag(name = "User API", description = "사용자 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 프로필 이미지 변경", description = "사용자의 프로필 이미지를 변경합니다.")
    @PutMapping("/{userId}/profile-image")
    public ResponseEntity<ApiResponse<Map<String, String>>> updateUserProfileImage(
            @PathVariable Long userId,
            @RequestParam("image") MultipartFile imageFile) {

        String newImageUrl = userService.updateUserProfileImage(userId, imageFile);
        return ResponseEntity.ok(ApiResponse.success(Map.of("imageUrl", newImageUrl)));
    }

    @Operation(summary = "사용자 배경 이미지 변경", description = "사용자의 배경 이미지를 변경합니다.")
    @PutMapping("/{userId}/background-image")
    public ResponseEntity<ApiResponse<Map<String, String>>> updateUserBackgroundImage(
            @PathVariable Long userId,
            @RequestParam("image") MultipartFile imageFile) {

        String newBackGroundImageUrl = userService.updateUserBackgroundImage(userId, imageFile);
        return ResponseEntity.ok(ApiResponse.success(Map.of("backGroundImageUrl", newBackGroundImageUrl)));
    }

    @Operation(summary = "사용자 프로필 페이지조회", description = "사용자의 프로필 페이지정보를 조회합니다.")
    @GetMapping("/{userId}/profileAll")
    public ResponseEntity<ApiResponse<UserProfileAllResponseDto>> getUserProfileAll(@PathVariable Long userId) {
        UserProfileAllResponseDto userProfile = userService.getUserProfileAll(userId);
        return ResponseEntity.ok(ApiResponse.success(userProfile));
    }

    @GetMapping("/{userId}/profile")
    public ApiResponse<UserProfileResponseDto> getUserProfile(@PathVariable Long userId) {
        UserProfileResponseDto profileDto = userService.getUserProfile(userId);
        return ApiResponse.success(profileDto);
    }

    @GetMapping("/{userId}/reservations")
    public ApiResponse<List<ReservationDetailDto> > getUserReservations(@PathVariable Long userId) {
        List<ReservationDetailDto> result = userService.getUserReservations(userId);
        return ApiResponse.success(result);
    }

//    @Operation(summary = "내 결제 수단 조회", description = "현재 로그인된 사용자의 결제 수단 목록을 조회합니다.")
//    @GetMapping("/payments")
//    public ApiResponse<List<UserProfilePaymentMethodDto>> getMyPaymentMethods(@AuthenticationPrincipal Long userId) {
//        List<UserProfilePaymentMethodDto> paymentMethods = userService.getUserPaymentMethods(userId);
//        return ApiResponse.success(paymentMethods);
//    }
    @Operation(summary = "특정 사용자 결제 수단 조회 (임시 테스트용)", description = "특정 사용자의 결제 수단 목록을 조회합니다.")
    @GetMapping("/{userId}/payments") // URL 경로에 {userId} 추가
    public ApiResponse<List<UserProfilePaymentMethodDto>> getMyPaymentMethods(@PathVariable Long userId) { // @PathVariable 추가
        List<UserProfilePaymentMethodDto> paymentMethods = userService.getUserPaymentMethods(userId);
        return ApiResponse.success(paymentMethods);
    }
    // 위에 코드로 사용해야 하는데 인증 구현전까지 임의로 사용


    @Operation(summary = "사용자 프로필 정보 수정", description = "사용자의 이름, 주소 등 텍스트 정보를 수정합니다.")
    @PutMapping("/{userId}/profile-info")
    public ResponseEntity<ApiResponse<UserProfileAllResponseDto>> updateUserProfileInfo(
            @PathVariable Long userId,
            @RequestBody UserProfileRequestDto requestDto) {

        UserProfileAllResponseDto updatedProfile = userService.updateUserProfileInfo(userId, requestDto);
        return ResponseEntity.ok(ApiResponse.success(updatedProfile));
    }


}