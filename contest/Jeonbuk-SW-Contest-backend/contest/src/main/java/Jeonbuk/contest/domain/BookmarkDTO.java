package Jeonbuk.contest.domain;

import Jeonbuk.contest.entity.enumType.BookmarkType;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkDTO {
    private String memberId;
    private Long bookmarkId;
    private BookmarkType bookmarkType;
    private String review;
    @Size(min = 1, max = 5)
    private int rating;

}
