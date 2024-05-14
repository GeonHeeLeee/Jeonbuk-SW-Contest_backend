package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.TownStroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownStrollRepository extends JpaRepository<TownStroll, Long> {
    @Query(value = "SELECT t FROM TownStroll t WHERE (6371000 * acos(cos(radians(:latitude)) * cos(radians(t.latitude)) * cos(radians(t.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(t.latitude)))) <= :radius")
    List<TownStroll> findWithinRadius(@Param("latitude") float latitude, @Param("longitude") float longitude, @Param("radius") float radius);

}
