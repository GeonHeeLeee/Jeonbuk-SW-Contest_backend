package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.safeReturn.StreetLamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetLampRepository extends JpaRepository<StreetLamp, Long> {
}
