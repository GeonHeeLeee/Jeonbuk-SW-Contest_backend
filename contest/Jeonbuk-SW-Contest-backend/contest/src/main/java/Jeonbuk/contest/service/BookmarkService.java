package Jeonbuk.contest.service;


import Jeonbuk.contest.domain.BookmarkDTO;
import Jeonbuk.contest.entity.Bookmark;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.entity.enumType.BookmarkType;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.BookmarkRepository;
import Jeonbuk.contest.repository.TownStrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberService memberService;
    private final RestaurantService restaurantService;
    private final DiscountStoreService discountStoreService;
    private final FestivalService festivalService;
    private final TownStrollRepository townStrollRepository;

    private boolean isBookmarkAlreadyRegistered(BookmarkType bookmarkType, String memberId, Long typeId) {
        return bookmarkRepository.existsByTypeAndTypeIdAndMember_Id(bookmarkType, typeId, memberId);
    }

    public ResponseEntity<?> registerBookmark(BookmarkDTO bookmarkDTO) {
        if (isBookmarkAlreadyRegistered(bookmarkDTO.getBookmarkType(), bookmarkDTO.getMemberId(), bookmarkDTO.getTypeId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.BOOKMARK_ALREADY_EXISTS);
        }
        Bookmark bookmark = createAndSaveBookmark(bookmarkDTO);
        return ResponseEntity.ok().body(Collections.singletonMap("bookmark", bookmark.getId()));
    }

    private Bookmark createAndSaveBookmark(BookmarkDTO bookmarkDTO) {
        Member member = memberService.findById(bookmarkDTO.getMemberId());
        Bookmark bookmark = Bookmark.builder()
                .member(member)
                .type(bookmarkDTO.getBookmarkType())
                .typeId(bookmarkDTO.getTypeId())
                .build();
        bookmarkRepository.save(bookmark);
        return bookmark;
    }


    @Transactional
    public ResponseEntity<Map<String, List>> getMemberBookmarkList(String memberId) {
        List<Bookmark> bookmarkList = bookmarkRepository.findBookmarkByMember_Id(memberId);
        Map<String, List> response = initBookmarkResponseMap();

        bookmarkList.forEach(bookmark -> {
                    Long typeId = bookmark.getTypeId();
                    switch (bookmark.getType()) {
                        case RESTAURANT -> response.get("RESTAURANT").add(restaurantService.findById(typeId));
                        case DISCOUNT_STORE -> response.get("DISCOUNT_STORE").add(discountStoreService.findById(typeId));
                        case FESTIVAL -> response.get("FESTIVAL").add(festivalService.findById(typeId));
                        case TOWN_STROLL -> response.get("TOWN_STROLL").add(townStrollRepository.findById(typeId));
                    }
                }
        );
        return ResponseEntity.ok().body(response);
    }

    private static Map<String, List> initBookmarkResponseMap() {
        Map<String, List> initMap = new HashMap<>();
        initMap.put("RESTAURANT", new ArrayList<>());
        initMap.put("DISCOUNT_STORE", new ArrayList<>());
        initMap.put("FESTIVAL", new ArrayList<>());
        initMap.put("TOWN_STROLL", new ArrayList<>());
        return initMap;
    }


    public ResponseEntity<?> deleteMemberBookmark(BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = bookmarkRepository.findByTypeAndTypeIdAndMember_Id(bookmarkDTO.getBookmarkType(), bookmarkDTO.getTypeId(), bookmarkDTO.getMemberId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.BOOKMARK_NOT_FOUND_ID));
        bookmarkRepository.delete(bookmark);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> checkMemberBookmark(String memberId, BookmarkType bookmarkType, Long typeId) {
        Bookmark bookmark = bookmarkRepository.findByTypeAndTypeIdAndMember_Id(bookmarkType, typeId, memberId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.BOOKMARK_ALREADY_EXISTS));
        return ResponseEntity.ok().body(Collections.singletonMap("bookmarkId", bookmark.getId()));
    }


}
