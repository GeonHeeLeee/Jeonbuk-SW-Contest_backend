package Jeonbuk.contest.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "음식점", description = "음식점 관련 API")
@Slf4j
@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    //전체 조회
    @Operation(summary = "전체 식당 조회")
    @GetMapping("/all")
    public ResponseEntity<Page<Restaurant>> getAllRestaurants(@Parameter(description = "페이지 번호(처음엔 0)") @RequestParam(value = "page") int page) {
        return restaurantService.getAllRestaurants(page);
    }
    //특정 필터로 조회
    //pageRequest로 조회해야됨
}
