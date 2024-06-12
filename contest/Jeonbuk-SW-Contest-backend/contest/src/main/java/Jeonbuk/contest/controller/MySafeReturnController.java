package Jeonbuk.contest.controller;

import Jeonbuk.contest.entity.safeReturn.MySafeReturn;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.MySafeReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@Tag(name = "내 안심귀가", description = "내 위치 기준 CCTV, 가로등, 비상벨")
@Slf4j
@RestController
@RequestMapping("/mySafeHome")
@RequiredArgsConstructor
public class MySafeReturnController {
    private final MySafeReturnService mySafeReturnService;

    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 CCTV, 가로등, 비상벨 조회", description = "content에 넣어 전체 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MySafeReturn.class),
                            examples = @ExampleObject(name = "content를 키로하여 반환", value = "{\"content\": [{\"id\": 1, \"latitude\": 35.0, \"longitude\": 128.0}]}")))
    })
    @GetMapping
    public ResponseEntity<Map<String, List<MySafeReturn>>> getSafeReturnAround(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                               @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                               @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return mySafeReturnService.getSafeReturnAround(latitude, longitude, radius);
    }


    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 CCTV, 가로등, 비상벨 필터링 조회", description = "Type을 키로하여 반환 (CCTV, StreetLamp, WarningBell)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MySafeReturn.class),
                            examples = @ExampleObject(name = "type: CCTV, StreetLamp, WarningBell", value = "{\"CCTV\": [{\"id\": 1, \"latitude\": 35.0, \"longitude\": 128.0}]}"))),
            @ApiResponse(responseCode = "400", description = "해당 Type이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @GetMapping("/filter")
    public ResponseEntity<Map<String, List<MySafeReturn>>> getSafeReturnAround(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                               @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                               @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius,
                                                                               @Parameter(description = "타입: CCTV, StreetLamp, WarningBell") @RequestParam("type") String type) {
        return mySafeReturnService.getSafeReturnAroundByType(latitude, longitude, radius, type);
    }
}
