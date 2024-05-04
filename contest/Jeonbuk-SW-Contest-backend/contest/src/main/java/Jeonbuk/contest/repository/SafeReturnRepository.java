package Jeonbuk.contest.repository;

import Jeonbuk.contest.domain.SafeReturnRequestDTO;
import Jeonbuk.contest.domain.SafeReturnResponseDTO;
import Jeonbuk.contest.entity.safeReturn.SafeReturn;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafeReturnRepository extends JpaRepository<SafeReturn, Long> {
    @Query("select new Jeonbuk.contest.domain.SafeReturnResponseDTO(s) from SafeReturn s where s.member.id = :memberId")
    List<SafeReturnResponseDTO> findAllByMemberId(@Param("memberId") String memberId);
}
