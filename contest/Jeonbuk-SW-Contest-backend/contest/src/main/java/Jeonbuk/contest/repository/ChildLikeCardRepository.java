package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.ChildLikeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildLikeCardRepository extends JpaRepository<ChildLikeCard, Long> {
}
