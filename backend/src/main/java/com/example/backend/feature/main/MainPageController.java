package com.example.backend.feature.main;

import com.example.backend.feature.main.dto.MainPageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Main API", description = "메인 페이지 관련 API") // http://localhost:8888/swagger-ui.html ㄱㄱㄱ
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainPageController {

    private final MainPageService mainPageService;

    @Operation(summary = "메인 페이지 데이터 조회", description = "메인 페이지에 필요한 모든 데이터를 조회합니다. (구독 서비스 신청 뺴고)")
    @GetMapping
    public ResponseEntity<MainPageResponseDto> getMainPage() {
        MainPageResponseDto mainPageData = mainPageService.getMainPageData();
        return ResponseEntity.ok(mainPageData);
    }
}