package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.restaurant.type.GoodPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodPriceRestaurantRepository extends JpaRepository<GoodPrice, Long> {
}
