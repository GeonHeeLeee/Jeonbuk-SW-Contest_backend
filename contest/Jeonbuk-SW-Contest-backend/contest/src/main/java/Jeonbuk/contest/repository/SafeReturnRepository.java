package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.safeReturn.SafeReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafeReturnRepository extends JpaRepository<SafeReturn, Long> {
}
