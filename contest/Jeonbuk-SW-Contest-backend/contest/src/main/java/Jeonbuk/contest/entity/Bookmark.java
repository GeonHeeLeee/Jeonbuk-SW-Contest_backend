package Jeonbuk.contest.entity;

import Jeonbuk.contest.entity.enumType.BookmarkType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private Long bookmarkId;
    private BookmarkType type;


    //Member가 해당 식당에 대한 Comment를 달 수 있도록
    private String review;

    //유저 별점
    @Size(min = 1, max = 5)
    private int rating;
}
