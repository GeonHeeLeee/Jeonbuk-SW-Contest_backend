package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.ModelRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRestaurantRepository extends JpaRepository<ModelRestaurant, Long> {
}
