package com.example.backend.room.entity;

import com.example.backend.hotel.entity.Hotel;
import com.example.backend.hotel.entity.HotelImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "room_img")
@NoArgsConstructor
public class RoomImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false, length = 100)
    private String fileName;

    @Column(name = "size", nullable = false)
    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomImg> images = new ArrayList<>();
}
