package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.restaurant.type.ChildMealCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildMealCardRepository extends JpaRepository<ChildMealCard, Long> {
}
