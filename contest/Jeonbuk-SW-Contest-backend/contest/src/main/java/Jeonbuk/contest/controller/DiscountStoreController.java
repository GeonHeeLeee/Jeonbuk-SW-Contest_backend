package Jeonbuk.contest.controller;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.service.DiscountStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "할인매장", description = "page 번호 처음엔 0, 이후 pageable의 PageNumber + 1로 요청")
@Slf4j
@RestController
@RequestMapping("/discountStore")
@RequiredArgsConstructor
public class DiscountStoreController {
    private final DiscountStoreService discountStoreService;

    //할인매장 - list(전체 - pageable)
    @Operation(summary = "전체 할인매장 조회 - list")
    @GetMapping("/list/all")
    public ResponseEntity<Page<DiscountStore>> getAllDiscountStoreForList(@Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return discountStoreService.getAllDiscountStoreForList(page);
    }
    //할인매장 - list(필터 - pageable)
    //할인매장 - map(전체)
    //할인매장 - map(필터)
}
