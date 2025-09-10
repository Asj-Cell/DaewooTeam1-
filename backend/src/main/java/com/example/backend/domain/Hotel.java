//package com.example.backend.domain;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import java.time.LocalDateTime; // DB의 datetime 타입은 LocalDateTime으로 매핑하는 것이 표준입니다.
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter @Setter
//@Entity
//@NoArgsConstructor
//@Table(name = "hotel")
//public class Hotel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "name", nullable = false, length = 200)
//    private String name;
//
//    @Column(name = "grade", nullable = false)
//    private int grade;
//
//    @Column(name = "overview", length = 100, nullable = false)
//    private String overview;
//
//    @Column(name = "latitude", nullable = false)
//    private double latitude;
//
//    @Column(name = "longitude", nullable = false)
//    private double longitude;
//
//    @Column(name = "address", nullable = false, length = 100)
//    private String address;
//
//    @Column(name = "checkin_time", nullable = false)
//    private LocalDateTime checkinTime;
//
//    @Column(name = "checkout_time", nullable = false)
//    private LocalDateTime checkoutTime;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "city_id")
//    private City city;
//
//    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Room> rooms = new ArrayList<>();
//
//    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
//    private Freebies freebies;
//
//    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
//    private Amenities amenities;
//
//}