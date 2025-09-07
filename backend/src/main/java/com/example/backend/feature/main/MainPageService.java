package com.example.backend.feature.main;

import com.example.backend.feature.main.dto.SearchDto;
import com.example.backend.feature.main.dto.MainPackageDto;
import com.example.backend.feature.main.dto.MainPageResponseDto;
import com.example.backend.feature.main.dto.PopularPackageDto;
import com.example.backend.repository.HotelRepository;
import com.example.backend.repository.TravelPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainPageService {

    private final HotelRepository hotelRepository;
    private final TravelPackageRepository travelPackageRepository;

    public MainPageResponseDto getMainPageData() {
        List<SearchDto> searchDtos = getSearch();

        List<PopularPackageDto> popularPackages = getPopularPackages();

        MainPackageDto mainPackage = getMainPackage();
        return new MainPageResponseDto(searchDtos, popularPackages, mainPackage);
    }

    private List<SearchDto> getSearch() {
        // 호텔 목록을 DestinationDto로 변환
        List<SearchDto> hotelDestinations = hotelRepository.findAll().stream()
                .map(hotel -> new SearchDto(
                        hotel.getId(),
                        hotel.getName() + ", " + hotel.getCity().getCityName() // "호텔이름, 도시이름" 형태로 화면에 나온데로 일단 만들어둠
                ))
                .toList();
        return hotelDestinations;
    }

    private List<PopularPackageDto> getPopularPackages() {
        // 위와 같이  패키지 클래스을 PopularPackageDto 로 변환
        return travelPackageRepository.findAll().stream()
                .map(pkg -> new PopularPackageDto(
                        pkg.getId(),
                        pkg.getTitle(),
                        pkg.getCity().getCityName(),
                        pkg.getPrice(),
                        pkg.getImages().getFirst().getImageUrl()
                ))
                .toList();
    }

    private MainPackageDto getMainPackage() {

        return travelPackageRepository.findAll().stream().findFirst()
                .map(pkg -> new MainPackageDto(
                        pkg.getId(),
                        pkg.getTitle(),
                        pkg.getDescription(),
                        pkg.getPrice(),
                        pkg.getImages().stream()
                                .map(img -> img.getImageUrl())
                                .toList()
                ))
                .orElse(null);
    }
}