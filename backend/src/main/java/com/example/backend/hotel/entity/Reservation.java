package com.example.backend.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name = "reservation")
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkinDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkoutDate;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "taxes")
    private Double taxes;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


}




