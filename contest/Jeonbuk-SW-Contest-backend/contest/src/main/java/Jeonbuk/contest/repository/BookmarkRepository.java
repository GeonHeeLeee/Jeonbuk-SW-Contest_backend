package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    //TODO - 작동안하는거 고치기

    List<Bookmark> findBookmarkByMember_Id(@Param("memberId") String memberId);
}
