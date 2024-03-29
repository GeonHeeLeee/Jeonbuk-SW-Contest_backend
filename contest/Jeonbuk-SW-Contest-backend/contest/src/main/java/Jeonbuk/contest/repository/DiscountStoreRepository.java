package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.DiscountStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountStoreRepository extends JpaRepository<DiscountStore, Long> {
}
