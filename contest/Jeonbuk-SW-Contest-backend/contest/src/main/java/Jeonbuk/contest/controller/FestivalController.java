package Jeonbuk.contest.controller;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.Festival;
import Jeonbuk.contest.service.BookmarkService;
import Jeonbuk.contest.service.FestivalService;
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

@Tag(name = "축제", description = "page 번호 처음엔 0, 이후 pageable의 PageNumber + 1로 요청")
@Slf4j
@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
public class FestivalController {

    private final BookmarkService bookmarkService;
    private final FestivalService festivalService;

    @Operation(summary = "전체 축제 조회 - list")
    @GetMapping("/list/all")
    public ResponseEntity<Page<Festival>> getAllFestivalPage(@Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return festivalService.getAllFestivalPage(page);
    }

}
