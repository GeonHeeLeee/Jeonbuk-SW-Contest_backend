package Jeonbuk.contest.entity;

import Jeonbuk.contest.entity.enumType.BookmarkType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private BookmarkType type;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "discountStoreId")
    private DiscountStore discountStore;

    //TODO- 별점, 리뷰 남기기
}
