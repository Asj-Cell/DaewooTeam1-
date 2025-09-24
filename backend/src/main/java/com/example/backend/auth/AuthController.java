package com.example.backend.auth;

import com.example.backend.auth.dto.*;
import com.example.backend.common.util.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal; // 추가
import org.springframework.security.core.userdetails.UserDetails; // 추가
import org.springframework.web.bind.annotation.*; // 와일드카드 import

@Tag(name = "Auth API", description = "인증(회원가입/로그인) 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody SignupRequestDto signupRequestDto) {
        authService.signup(signupRequestDto);
        return ResponseEntity.ok(ApiResponse.success("회원가입이 완료되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        AuthResponseDto response = authService.login(loginRequestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 1단계: 비밀번호 재설정 이메일(인증 코드) 발송 요청
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody ForgotPasswordRequestDto requestDto) {
        authService.forgotPassword(requestDto.getEmail());
        return ResponseEntity.ok(ApiResponse.success("비밀번호 재설정 이메일을 발송했습니다."));
    }

    // ▼▼▼▼▼ 2단계: 인증 코드 검증 후 임시 토큰 반환 ▼▼▼▼▼
    @PostMapping("/verify-code")
    public ResponseEntity<ApiResponse<VerifyCodeResponseDto>> verifyCode(@RequestBody VerifyCodeRequestDto requestDto) {
        VerifyCodeResponseDto response = authService.verifyResetToken(requestDto.getToken());
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    // ▲▲▲▲▲ 여기까지 수정 ▲▲▲▲▲

    // ▼▼▼▼▼ 3단계: 임시 토큰을 헤더로 받아 비밀번호 최종 변경 ▼▼▼▼▼
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ResetPasswordRequestDto requestDto) {

        if (userDetails == null) {
            // 이 로직은 JwtAuthenticationFilter에서 인증되지 않은 요청을 걸러주므로 사실상 실행될 일은 없습니다.
            // 하지만 방어적인 코드로 추가해두는 것이 좋습니다.
            return ResponseEntity.status(401).body(ApiResponse.fail("인증 정보가 유효하지 않습니다."));
        }

        if (!requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) {
            throw new IllegalArgumentException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Long userId = Long.parseLong(userDetails.getUsername());
        authService.resetPassword(userId, requestDto.getNewPassword());

        return ResponseEntity.ok(ApiResponse.success("비밀번호가 성공적으로 재설정되었습니다."));
    }
    // ▲▲▲▲▲ 여기까지 수정 ▲▲▲▲▲
}