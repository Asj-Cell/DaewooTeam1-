package com.example.backend.travelPackage;

import com.example.backend.travelPackage.dto.MainPackageDto;
import com.example.backend.travelPackage.dto.PopularPackageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;

    public List<PopularPackageDto> getPopularPackages() {
        return travelPackageRepository.findTop4ByOrderByIdDesc().stream()
                .map(pkg -> new PopularPackageDto(
                        pkg.getId(),
                        pkg.getTitle(),
                        pkg.getCity().getCityName(),
                        pkg.getPrice(),
                        pkg.getImages().getFirst().getImageUrl()
                ))
                .toList();
    }

    public MainPackageDto getMainPackage() {
        return travelPackageRepository.findFirstByOrderByIdAsc()
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