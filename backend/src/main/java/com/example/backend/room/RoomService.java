package com.example.backend.room;

import com.example.backend.room.dto.RoomDto;
import com.example.backend.room.dto.RoomImgDto;
import com.example.backend.room.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    // 특정 호텔의 모든 룸 조회
    public List<RoomDto> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 특정 룸 조회
    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        return convertToDto(room);
    }

    // Room -> RoomDto 변환 (필요한 정보만)
    private RoomDto convertToDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getRoomNumber(),
                room.getName(),
                room.getPrice(),
                room.getView(),
                room.getBed(),
                room.getMaxGuests(),
                room.getImages().stream()
                        .map(img -> new RoomImgDto(img.getId(), img.getImageUrl(),img.getSize()))
                        .collect(Collectors.toList())
        );
    }
}
