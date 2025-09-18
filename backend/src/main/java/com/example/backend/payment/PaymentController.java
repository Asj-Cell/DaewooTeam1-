package com.example.backend.payment;

import com.example.backend.common.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment API", description = "결제 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

//    @Operation(summary = "내 결제 수단 삭제", description = "현재 로그인된 사용자의 특정 결제 수단을 삭제합니다.")
//    @DeleteMapping("/payments/{paymentId}")
//    public ApiResponse<Void> deleteMyPaymentMethod(
//            @PathVariable Long paymentId,
//            @AuthenticationPrincipal Long userId) {
//
//        paymentService.deletePaymentMethod(paymentId, userId);
//        return ApiResponse.success("결제 수단이 성공적으로 삭제되었습니다.", null);
//    }


    // --- 임시 테스트용 대체 코드 ---
    @Operation(summary = "특정 사용자의 결제 수단 삭제 (임시 테스트용)", description = "특정 사용자의 결제 수단을 삭제합니다.")
    @DeleteMapping("/users/{userId}/payments/{paymentId}")
    public ApiResponse<Integer> deleteUserPaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long paymentId) {

        paymentService.deletePaymentMethod(paymentId, userId);
        return ApiResponse.success(111);
    }
}