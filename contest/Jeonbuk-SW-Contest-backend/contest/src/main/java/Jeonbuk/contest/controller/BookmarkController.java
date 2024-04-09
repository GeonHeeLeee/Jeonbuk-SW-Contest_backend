package Jeonbuk.contest.controller;


import Jeonbuk.contest.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "북마크")
@Slf4j
@Controller
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Operation(summary = "유저의 북마크 리스트 확인", description = "할인매장인 경우 restaurant == null, 식당인 경우 discountStore == null")
    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMemberBookmarkList(@Parameter(description = "사용자 ID") @PathVariable("memberId") String memberId) {

        return bookmarkService.getMemberBookmarkList(memberId);
    }


    @Operation(summary = "유저 북마크 삭제")
    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<?> deleteMemberBookmark(@Parameter(description = "북마크 ID") @PathVariable("bookmarkId") Long bookmarkId) {
        return bookmarkService.deleteMemberBookmark(bookmarkId);
    }

}
