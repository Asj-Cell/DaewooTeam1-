//@Tag(name = "Hotel Filter API", description = "호텔 필터링 관련 API")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/hotels")
//public class HotelFiltersController {
//
//    private final HotelFiltersService hotelFiltersService;
//
//    @Operation(summary = "호텔 필터링 조회", description = "사용자가 선택한 조건에 맞는 호텔을 조회합니다.")
//    @PostMapping("/filter")
//    public ResponseEntity<List<HotelDto>> filterHotels(@RequestBody HotelFilterRequestDto request) {
//        List<HotelDto> filteredHotels = hotelFiltersService.filterHotels(request);
//        return ResponseEntity.ok(filteredHotels);
//    }
//}
