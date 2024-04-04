package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.BookmarkDTO;
import Jeonbuk.contest.domain.BookmarkRegisterDTO;
import Jeonbuk.contest.entity.Bookmark;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.entity.enumType.BookmarkType;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.repository.BookmarkRepository;
import Jeonbuk.contest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static Jeonbuk.contest.exception.ErrorCode.MEMBER_NOT_FOUND_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;

    public ResponseEntity bookmarkRestaurant(BookmarkRegisterDTO bookmarkRegisterDTO) {
        Member member = memberRepository.findById(bookmarkRegisterDTO.getMemberId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, MEMBER_NOT_FOUND_ID));
        Bookmark bookmark = Bookmark.builder()
                .member(member)
                .type(BookmarkType.RESTAURANT)
                .review(null)
                .rating(0)
                .build();
        bookmarkRepository.save(bookmark);
        return ResponseEntity.ok().build();
    }
}
