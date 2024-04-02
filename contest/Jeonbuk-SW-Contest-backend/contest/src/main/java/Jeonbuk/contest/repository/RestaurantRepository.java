package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.enumType.Promotion;
import Jeonbuk.contest.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findAll(Pageable pageable);
    Page<Restaurant> findAllByPromotionType(Promotion promotionType, Pageable pageable);
    List<Restaurant> findAll();


    //반경 내에 있는 식당 찾기
    @Query(value = "SELECT r FROM Restaurant r WHERE (6371000 * acos(cos(radians(:latitude)) * cos(radians(r.latitude)) * cos(radians(r.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(r.latitude)))) <= :radius")
    List<Restaurant> findWithinRadius(@Param("latitude") float latitude, @Param("longitude") float longitude, @Param("radius") float radius);

    @Query(value = "SELECT r FROM Restaurant r WHERE ((6371000 * acos(cos(radians(:latitude)) * cos(radians(r.latitude)) * cos(radians(r.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(r.latitude)))) <= :radius) AND r.promotionType = :promotion")
    List<Restaurant> findByPromotionWithinRadius(@Param("latitude") float latitude, @Param("longitude") float longitude, @Param("radius") float radius, @Param("promotion") Promotion promotion);

}
