package com.example.backend.payment;

import com.example.backend.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public void deletePaymentMethod(Long paymentId, Long userId) {
        // 1. paymentId와 userId를 동시에 사용하여 본인의 결제 수단이 맞는지 확인
        Payment payment = paymentRepository.findByIdAndUserId(paymentId, userId)
                .orElseThrow(() -> new IllegalArgumentException("삭제 권한이 없거나 존재하지 않는 결제 수단입니다."));

        // 2. 소유권이 확인되면 삭제
        paymentRepository.delete(payment);
    }
}