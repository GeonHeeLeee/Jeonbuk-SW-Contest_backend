package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.Bookmark;
import Jeonbuk.contest.entity.enumType.BookmarkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findBookmarkByMember_Id(@Param("memberId") String memberId);

    Boolean existsByTypeAndTypeIdAndMember_Id(BookmarkType bookmarkType, Long typeId, String memberId);

    Optional<Bookmark> findByTypeAndTypeIdAndMember_Id(BookmarkType bookmarkType, Long typeId, String memberId);

    void deleteByMember_Id(String memberId);
}
