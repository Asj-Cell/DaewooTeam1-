package com.example.backend.amenities.entity;


import com.example.backend.hotel.entity.Hotel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "amenities")
@NoArgsConstructor
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "outdoor_poor")
    private Boolean outdoorPool;

    @Column(name = "indoor_poor")
    private Boolean indoorPool;

    @Column(name = "spa_wellness_center")
    private Boolean spaWellnessCenter;

    @Column(name = "restaurant")
    private Boolean restaurant;

    @Column(name = "room_service")
    private Boolean roomService;

    @Column(name = "fitness_center")
    private Boolean fitnessCenter;

    @Column(name = "bar_lounge")
    private Boolean barLounge;

    @Column(name = "tea_coffee_machine")
    private Boolean teaCoffeeMachine;

    @Column(name = "air_conditioning")
    private Boolean airConditioning;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

}
