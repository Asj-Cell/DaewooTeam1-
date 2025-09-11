//package com.example.backend.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Getter @Setter
//@Entity
//@Table(name = "reservation")
//@NoArgsConstructor
//public class Reservation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "check_in_date", nullable = false)
//    private LocalDate checkInDate;
//
//    @Column(name = "check_out_date", nullable = false)
//    private LocalDate checkOutDate;
//
//
//    @Column(name = "discount", precision = 10, scale = 2)
//    private BigDecimal discount;
//
//    @Column(name = "taxes", precision = 10, scale = 2)
//    private BigDecimal taxes;
//
//    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
//    private BigDecimal totalPrice;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id", nullable = false)
//    private Room room;
//
//}