package com.example.backend.user.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserReservationResponseDto {

    private final List<ReservationDetailDto> reservations;

    public UserReservationResponseDto(List<ReservationDetailDto> reservations) {
        this.reservations = reservations;
    }
}