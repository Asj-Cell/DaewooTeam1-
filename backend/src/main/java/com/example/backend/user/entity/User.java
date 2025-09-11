package com.example.backend.user.entity;


import com.example.backend.review.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name",nullable = false, length = 100)
    private String userName;

    @Column(name = "email",nullable = false, length = 100)
    private String email;

    @Column(name = "password",nullable = false, length = 100)
    private String password;

    @Column(name = "phone_number",nullable = false, length = 100)
    private String phoneNumber;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "image_url",length = 255)
    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
