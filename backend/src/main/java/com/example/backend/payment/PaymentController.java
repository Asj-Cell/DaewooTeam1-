package com.example.backend.payment;

import com.example.backend.common.util.ApiResponse;
import com.example.backend.payment.dto.PaymentRequestDto;
import com.example.backend.payment.dto.PaymentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment API", description = "결제 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 수단 추가 (임시 테스트용)", description = "특정 사용자의 결제 수단을 추가합니다.")
    @PostMapping("/users/{userId}/payments")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> addUserPaymentMethod(
            @PathVariable Long userId,
            @Valid @RequestBody PaymentRequestDto requestDto) {

        PaymentResponseDto newPayment = paymentService.addPaymentMethod(userId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(newPayment));
    }


    @Operation(summary = "특정 사용자의 결제 수단 삭제 (임시 테스트용)", description = "특정 사용자의 결제 수단을 삭제합니다.")
    @DeleteMapping("/users/{userId}/payments/{paymentId}")
    public ApiResponse<String> deleteUserPaymentMethod(
                                                        @PathVariable Long userId,
                                                        @PathVariable Long paymentId) {

        paymentService.deletePaymentMethod(paymentId, userId);
        return ApiResponse.success("결제 수단이 성공적으로 삭제되었습니다.");
    }
}