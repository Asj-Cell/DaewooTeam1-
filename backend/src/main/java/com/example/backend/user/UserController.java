package com.example.backend.user;

import com.example.backend.common.util.ApiResponse;
import com.example.backend.user.dto.UserProfileAllResponseDto; // 추가
import com.example.backend.user.dto.UserProfileResponseDto;
import com.example.backend.user.dto.UserReservationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "사용자 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
    public ApiResponse<UserReservationResponseDto> getUserReservations(@PathVariable Long userId) {
        UserReservationResponseDto responseDto = userService.getUserReservations(userId);
        return ApiResponse.success(responseDto);
    }

}