package com.example.backend.user;

import com.example.backend.common.util.ApiResponse;
import com.example.backend.user.dto.UserProfileResponseDto; // 추가
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

    @Operation(summary = "사용자 프로필 조회", description = "사용자의 프로필 정보를 조회합니다.")
    @GetMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<UserProfileResponseDto>> getUserProfile(@PathVariable Long userId) {
        UserProfileResponseDto userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(ApiResponse.success(userProfile));
    }


}