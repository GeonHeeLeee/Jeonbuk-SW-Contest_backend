package Jeonbuk.contest.controller;


import Jeonbuk.contest.service.BookmarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "북마크")
@Slf4j
@Controller
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

//    @Operation(summary = "유저의 북마크 리스트 확인",
//            description = "memberId(사용자ID)와 페이지 번호(0부터 시작) 요청")
//    @GetMapping("/{memberId}/{page}")
//    public ResponseEntity<Page<Bookmark>> getMemberBookmarkList(@Parameter(description = "사용자 ID") @PathVariable("memberId") String memberId,
//                                                                @Parameter(description = "페이지 번호") @PathVariable("page") int page) {
//
////        return bookmarkService.getMemberBookmarkList(memberId, page);
//    }

}
