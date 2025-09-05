package com.example.backend.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Room_id")
    private Long id;

    @Column(nullable = false)
    private Integer price;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20)
    private String view;

    @Column(length = 50)
    private String bed;

    @Column(length = 50, nullable = false)
    private String roomsGuest;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
}
