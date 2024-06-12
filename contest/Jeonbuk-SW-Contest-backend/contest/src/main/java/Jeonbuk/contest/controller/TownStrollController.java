package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.BookmarkDTO;
import Jeonbuk.contest.entity.TownStroll;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.BookmarkService;
import Jeonbuk.contest.service.TownStrollService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "동네 마실", description = "주변 동네 마실 검색")
@Slf4j
@RestController
@RequestMapping("/townStroll")
@RequiredArgsConstructor
public class TownStrollController {
    private final TownStrollService townStrollService;
    private final BookmarkService bookmarkService;

    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 동네 마실 조회 - Map",
            description = "Map으로 반환. key: townStrollList, value: 동네 마실 리스트")
    @GetMapping("/map/all")
    public ResponseEntity<Map<String, List<TownStroll>>> getAllDiscountStoreWithinRadius(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                                         @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                                         @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return townStrollService.getAllTownStrollWithinRadius(latitude, longitude, radius);
    }


    @Operation(summary = "동네마실 즐겨찾기 등록", description = "memberId: 사용자ID, typeId: 해당 할인매장의 Id, bookmarkType: RESTAURANT(식당), DISCOUNT_STORE(할인매장), FESTIVAL(축제), TOWN_STROLL(동네 마실)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "북마크 등록 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "북마크 Id", value = "{\"bookmarkId\": \"value\"}")
                    ))})
    @ApiResponse(responseCode = "400", description = "북마크 중복 등록/BookmarkType 요청 오류", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    @PostMapping("/bookmark")
    public ResponseEntity<?> bookmarkFestival(@RequestBody BookmarkDTO bookmarkDTO) {
        return bookmarkService.registerBookmark(bookmarkDTO);
    }
}
