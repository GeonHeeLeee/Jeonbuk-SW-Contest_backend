package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.BookmarkRegisterDTO;
import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.entity.enumType.Promotion;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.BookmarkService;
import Jeonbuk.contest.service.RestaurantService;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "음식점", description = "page 번호 처음엔 0, 이후 pageable의 PageNumber + 1로 요청")
@Slf4j
@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final BookmarkService bookmarkService;

    //전체 조회
    @Operation(summary = "전체 식당 조회 - List")
    @GetMapping("/list/all")
    public ResponseEntity<Page<Restaurant>> getAllRestaurantsPage(@Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return restaurantService.getAllRestaurantsPage(page);
    }

    @Operation(summary = "특정 필터 음식점 조회 - List",
            description = "Promotion Type 종류 - 착한가격업소: GOOD_PRICE, 아이조아카드: CHILD_LIKE, 아동급식카드: CHILD_MEAL, 모범음식점: MODEL, 문화누리카드: CULTURE_NURI")
    @GetMapping("/list/{promotion}")
    public ResponseEntity<Page<Restaurant>> getRestaurantsByPromotionPage(@Parameter(description = "Promotion Type") @PathVariable(value = "promotion") Promotion promotionType,
                                                                          @Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return restaurantService.getRestaurantsByPromotionPage(promotionType, page);
    }

    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 식당 조회 - Map",
            description = "content에 넣어 전체 반환")
    @GetMapping("/map/all")
    public ResponseEntity<Map<String, List<Restaurant>>> getAllRestaurantsWithinRadius(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                                       @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                                       @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return restaurantService.getAllRestaurantsWithinRadius(latitude, longitude, radius);
    }

    @Operation(summary = "기준점(위도, 경도) 반경 내, 특정 필터 음식점 조회 - Map",
            description = "Promotion Type 종류 - 착한가격업소: GOOD_PRICE, 아이조아카드: CHILD_LIKE, 아동급식카드: CHILD_MEAL, 모범음식점: MODEL, 문화누리카드: CULTURE_NURI")
    @GetMapping("/map/{promotion}")
    public ResponseEntity<Map<String, List<Restaurant>>> getRestaurantsByPromotionTypeWithinRadius(@Parameter(description = "Promotion Type") @PathVariable(value = "promotion") Promotion promotionType,
                                                                                                   @Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                                                   @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                                                   @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return restaurantService.getRestaurantsByPromotionTypeWithinRadius(latitude, longitude, radius, promotionType);
    }

    @Operation(summary = "식당 즐겨찾기 등록", description = "memberId: 사용자ID, storeId: 해당 식당의 Id, bookmarkType: RESTAURANT(식당), DISCOUNT_STORE(할인매장)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "북마크 등록 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "북마크 Id", value = "{\"bookmarkId\": \"value\"}")
                    )),
            @ApiResponse(responseCode = "400", description = "북마크 중복 등록/BookmarkType 요청 오류", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/bookmark")
    public ResponseEntity<?> bookmarkRestaurant(@RequestBody BookmarkRegisterDTO bookmarkRegisterDTO) {
        return bookmarkService.registerBookmark(bookmarkRegisterDTO);
    }
}
