package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.enumType.BusinessCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountStoreRepository extends JpaRepository<DiscountStore, Long> {
    Page<DiscountStore> findAll(Pageable pageable);

    Page<DiscountStore> findAllByCategory(Pageable pageable, BusinessCategory category);


    @Query(value = "SELECT d FROM DiscountStore d WHERE (6371000 * acos(cos(radians(:latitude)) * cos(radians(d.latitude)) * cos(radians(d.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(d.latitude)))) <= :radius")
    List<DiscountStore> findWithinRadius(@Param("latitude") float latitude, @Param("longitude") float longitude, @Param("radius") float radius);

    @Query(value = "SELECT d FROM DiscountStore d WHERE ((6371000 * acos(cos(radians(:latitude)) * cos(radians(d.latitude)) * cos(radians(d.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(d.latitude)))) <= :radius) AND d.category = :category")
    List<DiscountStore> findByCategoryWithinRadius(@Param("latitude") float latitude, @Param("longitude") float longitude, @Param("radius") float radius, @Param("category") BusinessCategory category);

}
