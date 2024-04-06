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
    //Member가 해당 식당에 대한 Comment를 달 수 있도록
//    private String review;
//
//    //유저 별점
//    @Size(min = 0, max = 5)
//    private int rating;
}
