package Jeonbuk.contest.controller;

import Jeonbuk.contest.service.MySafeReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "내 안심귀가", description = "첫 화면은 반경 입력해서 주변 CCTV 등의 정보 가져오기")
@Slf4j
@RestController
@RequestMapping("/mySafeHome")
@RequiredArgsConstructor
public class MySafeReturnController {
    private final MySafeReturnService mySafeReturnService;

    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 CCTV, 가로등, 비상벨 조회",
            description = "content에 넣어 전체 반환 - 추후 type을 이용하여 마커를 다르게 표시(CCTV, StreetLamp, WarningBell")
    @GetMapping("/")
    public ResponseEntity<?> getSafeReturnAround(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                 @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                 @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return mySafeReturnService.getSafeReturnAround(latitude, longitude, radius);
    }

    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 CCTV, 가로등, 비상벨 필터링 조회",
            description = "Type을 키로하여 반환 (CCTV, StreetLamp, WarningBell)")
    @GetMapping("/filter")
    public ResponseEntity<?> getSafeReturnAround(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                 @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                 @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius,
                                                 @Parameter(description = "타입: CCTV, StreetLamp, WarningBell") @RequestParam("type") String type) {
        return mySafeReturnService.getSafeReturnAroundByType(latitude, longitude, radius, type);
    }
}
