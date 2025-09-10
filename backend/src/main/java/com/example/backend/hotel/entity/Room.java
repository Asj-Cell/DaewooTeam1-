package com.example.backend.hotel.entity;

public class Room {
<<<<<<< Updated upstream:backend/src/main/java/com/example/backend/domain/Room.java
}
=======

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "view", length = 20)
    private String view;

    @Column(name = "bed", length = 50)
    private String bed;

    @Column(name = "max_guests", nullable = false)
    private Integer maxGuests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
>>>>>>> Stashed changes:backend/src/main/java/com/example/backend/hotel/entity/Room.java
