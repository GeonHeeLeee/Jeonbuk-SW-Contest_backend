package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.CultureNuriCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureNuriCardRepository extends JpaRepository<CultureNuriCard, Long> {
}
