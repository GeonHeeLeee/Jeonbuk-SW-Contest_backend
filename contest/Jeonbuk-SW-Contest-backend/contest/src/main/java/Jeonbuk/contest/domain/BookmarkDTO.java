package Jeonbuk.contest.domain;

import Jeonbuk.contest.entity.Bookmark;
import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.entity.enumType.BookmarkType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkDTO {
    private Long id;
    private BookmarkType type;
    private String memberId;
    private Restaurant restaurant;
    private DiscountStore discountStore;

    public BookmarkDTO(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.memberId = bookmark.getMember().getId();
        this.type = bookmark.getType();
        this.restaurant = bookmark.getRestaurant();
        this.discountStore = bookmark.getDiscountStore();
    }

}