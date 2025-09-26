package com.example.backend.Reservation;

import com.example.backend.pay.dto.PaymentPageDto; // 방금 만든 DTO
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    // 이 서비스는 잠시 후 ReservationService에서 만들 예정입니다.
    private final ReservationService reservationService;

    @GetMapping("/preview")
    public ResponseEntity<PaymentPageDto> getPaymentPreview(
            @RequestParam Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {

        PaymentPageDto previewDetails = reservationService.getPaymentPreviewDetails(roomId, checkInDate, checkOutDate);
        return ResponseEntity.ok(previewDetails);
    }
}
