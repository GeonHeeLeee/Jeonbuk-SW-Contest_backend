package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.TownStroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownStrollRepository extends JpaRepository<TownStroll, Long> {
}
