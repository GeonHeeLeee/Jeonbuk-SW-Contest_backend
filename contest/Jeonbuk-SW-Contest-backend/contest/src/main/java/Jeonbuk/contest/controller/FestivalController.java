package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.BookmarkDTO;
import Jeonbuk.contest.entity.Festival;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.BookmarkService;
import Jeonbuk.contest.service.FestivalService;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "축제", description = "전북의 축제 ")
@Slf4j
@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
public class FestivalController {

    private final BookmarkService bookmarkService;
    private final FestivalService festivalService;

    @Operation(summary = "전체 축제 조회 - list", description = "page 번호 처음엔 0, 이후 pageable의 PageNumber + 1로 요청")
    @GetMapping("/list/all")
    public ResponseEntity<Page<Festival>> getAllFestivalPage(@Parameter(description = "페이지 번호") @RequestParam(value = "page") int page) {
        return festivalService.getAllFestivalPage(page);
    }

    @Operation(summary = "축제 즐겨찾기 등록", description = "memberId: 사용자ID, typeId: 해당 할인매장의 Id, bookmarkType: RESTAURANT(식당), DISCOUNT_STORE(할인매장), FESTIVAL(축제), TOWN_STROLL(동네 마실)")
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
