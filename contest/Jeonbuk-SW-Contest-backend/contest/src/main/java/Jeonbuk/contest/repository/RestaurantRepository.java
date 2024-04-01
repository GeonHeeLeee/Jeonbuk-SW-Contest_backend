package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.PROMOTION_TYPE;
import Jeonbuk.contest.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findAll(Pageable pageable);

    Page<Restaurant> findAllByPromotionType(PROMOTION_TYPE promotionType, Pageable pageable);
}
