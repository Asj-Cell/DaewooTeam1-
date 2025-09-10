package com.example.backend.hotel.entity;

public class Reservation {
<<<<<<< Updated upstream:backend/src/main/java/com/example/backend/domain/Reservation.java
}
=======

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
>>>>>>> Stashed changes:backend/src/main/java/com/example/backend/hotel/entity/Reservation.java
