package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.BookmarkDTO;
import Jeonbuk.contest.domain.BookmarkRegisterDTO;
import Jeonbuk.contest.entity.Bookmark;
import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.entity.enumType.BookmarkType;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberService memberService;
    private final RestaurantService restaurantService;
    private final DiscountStoreService discountStoreService;

    private boolean isBookmarkAlreadyRegistered(BookmarkType bookmarkType, String memberId, Long storeId) {
        switch (bookmarkType) {
            case RESTAURANT:
                return bookmarkRepository.existsByRestaurant_IdAndMember_Id(storeId, memberId);
            case DISCOUNT_STORE:
                return bookmarkRepository.existsByDiscountStore_IdAndMember_Id(storeId, memberId);
            default:
                throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.BOOKMARK_TYPE_UNSUPPORTED);
        }
    }

    public ResponseEntity<?> registerBookmark(BookmarkRegisterDTO bookmarkRegisterDTO) {
        if (isBookmarkAlreadyRegistered(bookmarkRegisterDTO.getBookmarkType(), bookmarkRegisterDTO.getMemberId(), bookmarkRegisterDTO.getStoreId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.BOOKMARK_ALREADY_EXISTS);
        }

        Bookmark bookmark = createAndSaveBookmark(bookmarkRegisterDTO);
        return ResponseEntity.ok().body(Collections.singletonMap("bookmarkId", bookmark.getId()));
    }

    private Bookmark createAndSaveBookmark(BookmarkRegisterDTO bookmarkRegisterDTO) {
        Member member = memberService.findById(bookmarkRegisterDTO.getMemberId());
        Bookmark bookmark = Bookmark.builder()
                .member(member)
                .type(bookmarkRegisterDTO.getBookmarkType())
                .build();

        setStoreByBookmarkType(bookmarkRegisterDTO, bookmark);
        bookmarkRepository.save(bookmark);
        return bookmark;
    }


    @Transactional
    public ResponseEntity getMemberBookmarkList(String memberId) {
        List<BookmarkDTO> bookmarkList = bookmarkRepository.findBookmarkByMember_Id(memberId)
                .stream().map(BookmarkDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookmarkList);
    }

    private void setStoreByBookmarkType(BookmarkRegisterDTO bookmarkRegisterDTO, Bookmark bookmark) {
        BookmarkType bookmarkType = bookmarkRegisterDTO.getBookmarkType();
        if (bookmarkType == BookmarkType.RESTAURANT) {
            Restaurant restaurant = restaurantService.findById(bookmarkRegisterDTO.getStoreId());
            bookmark.setRestaurant(restaurant);
            bookmark.setDiscountStore(null);
        } else if (bookmarkType == BookmarkType.DISCOUNT_STORE) {
            DiscountStore discountStore = discountStoreService.findById(bookmarkRegisterDTO.getStoreId());
            bookmark.setDiscountStore(discountStore);
            bookmark.setRestaurant(null);
        }
    }

    public ResponseEntity<?> deleteMemberBookmark(Long bookmarkId) {
        try {
            bookmarkRepository.deleteById(bookmarkId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.BOOKMARK_NOT_FOUND_ID);
        }
        return ResponseEntity.ok().build();
    }

}
