package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.safeReturn.MySafeReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySafeReturnRepository extends JpaRepository<MySafeReturn, Long> {
    @Query(value = "SELECT s FROM MySafeReturn s WHERE (6371000 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) * cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude)))) <= :radius")
    List<MySafeReturn> findWithinRadius(@Param("latitude") float latitude, @Param("longitude") float longitude, @Param("radius") float radius);

}
