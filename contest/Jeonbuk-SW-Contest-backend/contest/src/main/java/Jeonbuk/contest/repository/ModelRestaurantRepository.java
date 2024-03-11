package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.restaurant.type.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRestaurantRepository extends JpaRepository<Model, Long> {
}
