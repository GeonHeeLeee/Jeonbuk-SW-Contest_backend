package Jeonbuk.contest.domain;

import Jeonbuk.contest.entity.enumType.BookmarkType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkDTO {
    private String memberId;
    private Long typeId;
    private BookmarkType bookmarkType;
}
