package Jeonbuk.contest.repository;

import Jeonbuk.contest.domain.ReturnRouteResponseDTO;
import Jeonbuk.contest.entity.safeReturn.ReturnRoute;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnRouteRepository extends JpaRepository<ReturnRoute, Long> {
    @Query("select new Jeonbuk.contest.domain.ReturnRouteResponseDTO(r) from ReturnRoute r where r.member.id = :memberId")
    List<ReturnRouteResponseDTO> findAllByMemberId(@Param("memberId") String memberId);

    void deleteByMember_Id(String memberId);
}
