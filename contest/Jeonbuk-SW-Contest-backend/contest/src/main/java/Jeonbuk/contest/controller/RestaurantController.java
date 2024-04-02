package Jeonbuk.contest.controller;

import Jeonbuk.contest.entity.enumType.Promotion;
import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Tag(name = "음식점", description = "page 번호 처음엔 0, 이후 pageable의 PageNumber + 1로 요청")
@Slf4j
@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    //전체 조회
    @Operation(summary = "전체 식당 조회 - List")
    @GetMapping("/list/all")
    public ResponseEntity<Page<Restaurant>> getAllRestaurantsForList(@Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return restaurantService.getAllRestaurantsForList(page);
    }

    @Operation(summary = "특정 필터 음식점 조회 - List",
            description = "Promotion Type 종류 - 착한가격업소: GOOD_PRICE, 아이조아카드: CHILD_LIKE, 아동급식카드: CHILD_MEAL, 모범음식점: MODEL, 문화누리카드: CULTURE_NURI")
    @GetMapping("/list/{promotion}")
    public ResponseEntity<Page<Restaurant>> getRestaurantsByPromotionTypeForList(@Parameter(description = "Promotion Type") @PathVariable(value = "promotion") Promotion promotionType,
                                                                                 @Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return restaurantService.getRestaurantsByPromotionTypeForList(promotionType, page);
    }

    @Operation(summary = "전체 식당 조회 - Map",
            description = "식당의 Promotion Type(GOOD_PRICE, CHILD_LIKE 등)에 따라 분류하여 전체 반환")
    @GetMapping("/map/all")
    public ResponseEntity<Map<Promotion, List<Restaurant>>> getAllRestaurantsForMap() {
        return restaurantService.getAllRestaurantsForMap();
    }

    @Operation(summary = "특정 필터 음식점 조회 - Map",
            description = "Promotion Type 종류 - 착한가격업소: GOOD_PRICE, 아이조아카드: CHILD_LIKE, 아동급식카드: CHILD_MEAL, 모범음식점: MODEL, 문화누리카드: CULTURE_NURI")
    @GetMapping("/map/{promotion}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByPromotionTypeForMap(@Parameter(description = "Promotion Type") @PathVariable(value = "promotion") Promotion promotionType) {
        return restaurantService.getRestaurantsByPromotionTypeForMap(promotionType);
    }
}
