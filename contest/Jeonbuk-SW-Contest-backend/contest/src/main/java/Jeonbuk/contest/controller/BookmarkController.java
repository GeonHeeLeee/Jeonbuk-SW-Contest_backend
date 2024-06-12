package Jeonbuk.contest.controller;


import Jeonbuk.contest.domain.BookmarkDTO;
import Jeonbuk.contest.entity.enumType.BookmarkType;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.BookmarkService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "북마크")
@Slf4j
@Controller
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Operation(summary = "사용자의 북마크 리스트 확인", description = "사용자의 ID로 북마크 조회")
    @GetMapping("/{memberId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Json 형태 type마다 리스트로 북마크 반환 - key: RESTAURANT, TOWN_STROLL, DISCOUNT_STORE, FESTIVAL value: List",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "Json 리스트", value = "{\"RESTAURANT\": \"[]\"," +
                                    "\"RESTAURANT\": \"[]\"}")
                    ))
    })
    public ResponseEntity<Map<String, List>> getMemberBookmarkList(@Parameter(description = "사용자 ID") @PathVariable("memberId") String memberId) {

        return bookmarkService.getMemberBookmarkList(memberId);
    }


    @Operation(summary = "사용자 북마크 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMemberBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
        return bookmarkService.deleteMemberBookmark(bookmarkDTO);
    }

    @Operation(summary = "사용자 북마크 확인", description = "북마크 존재하는 경우 bookmarkId 반환")
    @GetMapping("/check")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "북마크 존재",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "북마크 Id", value = "{\"bookmarkId\": \"value\"}")
                    )),
            @ApiResponse(responseCode = "400", description = "북마크 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<?> checkMemberBookmark(@Parameter(description = "사용자 ID") @RequestParam("memberId") String memberId,
                                                 @Parameter(description = "DISCOUNT_STORE | RESTAURANT | FESTIVAL | TOWN_STROLL") @RequestParam("bookmarkType") BookmarkType type,
                                                 @Parameter(description = "type(식당, 할인 매장, 축제 등)의 ID") @RequestParam("typeId") Long typeId) {
        return bookmarkService.checkMemberBookmark(memberId, type, typeId);
    }

}
