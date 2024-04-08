package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.BookmarkDTO;
import Jeonbuk.contest.domain.BookmarkRegisterDTO;
import Jeonbuk.contest.entity.Bookmark;
import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.entity.enumType.BookmarkType;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.repository.BookmarkRepository;
import Jeonbuk.contest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static Jeonbuk.contest.exception.ErrorCode.MEMBER_NOT_FOUND_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final RestaurantService restaurantService;
    private final DiscountStoreService discountStoreService;

    public ResponseEntity bookmarkStore(BookmarkRegisterDTO bookmarkRegisterDTO) {
        Member member = memberRepository.findById(bookmarkRegisterDTO.getMemberId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, MEMBER_NOT_FOUND_ID));
        Bookmark bookmark = Bookmark.builder()
                .member(member)
                .type(bookmarkRegisterDTO.getBookmarkType())
                .build();
        setStoreByBookmarkType(bookmarkRegisterDTO, bookmark);

        bookmarkRepository.save(bookmark);
        return ResponseEntity.ok().build();
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

}
