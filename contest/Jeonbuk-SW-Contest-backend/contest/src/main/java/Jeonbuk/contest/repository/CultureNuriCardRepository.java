package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.restaurant.type.CultureNuriCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureNuriCardRepository extends JpaRepository<CultureNuriCard, Long> {
}
