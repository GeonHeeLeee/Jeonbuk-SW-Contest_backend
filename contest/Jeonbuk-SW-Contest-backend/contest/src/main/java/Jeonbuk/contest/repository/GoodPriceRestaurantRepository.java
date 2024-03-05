package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.GoodPriceRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodPriceRestaurantRepository extends JpaRepository<GoodPriceRestaurant, Long> {
}
