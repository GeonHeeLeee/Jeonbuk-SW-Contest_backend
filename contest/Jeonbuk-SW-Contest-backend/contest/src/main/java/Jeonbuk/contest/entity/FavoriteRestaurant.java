package Jeonbuk.contest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class FavoriteRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    //To-Do: 식당 Entity Mapping
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "discountStoreId")
    private DiscountStore discountStore;

    //Member가 해당 식당에 대한 Comment를 달 수 있도록
    private String review;

    //유저 별점
    @Size(min = 1, max = 5)
    private int rating;
}
