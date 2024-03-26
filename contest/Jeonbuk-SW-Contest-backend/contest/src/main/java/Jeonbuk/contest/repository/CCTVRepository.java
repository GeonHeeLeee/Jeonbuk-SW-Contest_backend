package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.safeReturn.CCTV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CCTVRepository extends JpaRepository<CCTV, Long> {
}
