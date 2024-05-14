package Jeonbuk.contest.controller;

import Jeonbuk.contest.entity.TownStroll;
import Jeonbuk.contest.service.TownStrollService;
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

import java.util.List;
import java.util.Map;

@Tag(name = "동네 마실", description = "주변 동네 마실 검색")
@Slf4j
@RestController
@RequestMapping("/townStroll")
@RequiredArgsConstructor
public class TownStrollController {
    private final TownStrollService townStrollService;


    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 동네 마실 조회 - Map",
            description = "townStrollList에 넣어 전체 반환")
    @GetMapping("/map/all")
    public ResponseEntity<Map<String, List<TownStroll>>> getAllDiscountStoreWithinRadius(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                                         @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                                         @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return townStrollService.getAllTownStrollWithinRadius(latitude, longitude, radius);
    }
}
