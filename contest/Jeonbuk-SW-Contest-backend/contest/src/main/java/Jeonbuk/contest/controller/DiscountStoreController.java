package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.BookmarkRegisterDTO;
import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.enumType.BusinessCategory;
import Jeonbuk.contest.service.BookmarkService;
import Jeonbuk.contest.service.DiscountStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "할인매장", description = "page 번호 처음엔 0, 이후 pageable의 PageNumber + 1로 요청")
@Slf4j
@RestController
@RequestMapping("/discountStore")
@RequiredArgsConstructor
public class DiscountStoreController {
    private final DiscountStoreService discountStoreService;
    private final BookmarkService bookmarkService;

    @Operation(summary = "전체 할인매장 조회 - list")
    @GetMapping("/list/all")
    public ResponseEntity<Page<DiscountStore>> getAllDiscountStorePage(@Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return discountStoreService.getAllDiscountStorePage(page);
    }

    @Operation(summary = "특정 필터 할인매장 조회 - List",
            description = "Category 종류 - LEISURE: 여가/레저, SERVICES: 서비스업, FOOD: 음식, GOODS: 잡화, ETC: 기타, FOOD_BEVERAGE: 식품/음료, BOOKS_STATIONERY: 도서/문구 RETAIL: 도소매, EDUCATION: 교육, AUTOMOTIVE: 자동차/주유")
    @GetMapping("/list/{category}")
    public ResponseEntity<Page<DiscountStore>> getDiscountStoreByCategoryPage(@Parameter(description = "페이지 번호") @RequestParam(value = "page") int page,
                                                                              @Parameter(description = "카테고리") @PathVariable(value = "category") BusinessCategory category) {
        return discountStoreService.getDiscountStoreByCategoryPage(page, category);
    }

    @Operation(summary = "기준점(위도, 경도) 반경 내, 전체 할인매장 조회 - Map",
            description = "식당의 BusinessCategory(LEISURE, SERVICES 등)에 따라 분류하여 전체 반환")
    @GetMapping("/map/all")
    public ResponseEntity<Map<BusinessCategory, List<DiscountStore>>> getAllDiscountStoreWithinRadius(@Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                                                      @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                                                      @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return discountStoreService.getAllDiscountStoreWithinRadius(latitude, longitude, radius);
    }

    @Operation(summary = "기준점(위도, 경도) 반경 내, 특정 필터 할인매장 조회 - Map",
            description = "Category 종류 - LEISURE: 여가/레저, SERVICES: 서비스업, FOOD: 음식, GOODS: 잡화, ETC: 기타, FOOD_BEVERAGE: 식품/음료, BOOKS_STATIONERY: 도서/문구 RETAIL: 도소매, EDUCATION: 교육, AUTOMOTIVE: 자동차/주유")
    @GetMapping("/map/{category}")
    public ResponseEntity<List<DiscountStore>> getDiscountStoreByCategoryWithinRadius(@Parameter(description = "카테고리") @PathVariable(value = "category") BusinessCategory category,
                                                                                      @Parameter(description = "위도") @RequestParam("latitude") float latitude,
                                                                                      @Parameter(description = "경도") @RequestParam("longitude") float longitude,
                                                                                      @Parameter(description = "반지름(미터)") @RequestParam("radius") float radius) {
        return discountStoreService.getDiscountStoreByCategoryWithinRadius(latitude, longitude, radius, category);
    }

    @Operation(summary = "할인매장 즐겨찾기 등록", description = "memberId: 사용자ID, storeId: 해당 할인매장의 Id, bookmarkType: RESTAURANT(식당), DISCOUNT_STORE(할인매장)")
    @PostMapping("/bookmark")
    public ResponseEntity<?> bookmarkDiscountStore(@RequestBody BookmarkRegisterDTO bookmarkRegisterDTO) {
        return bookmarkService.bookmarkStore(bookmarkRegisterDTO);
    }

}
