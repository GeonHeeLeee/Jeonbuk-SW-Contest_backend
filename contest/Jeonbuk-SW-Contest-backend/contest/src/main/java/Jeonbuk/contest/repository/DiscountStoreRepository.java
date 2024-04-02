package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.enumType.BusinessCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountStoreRepository extends JpaRepository<DiscountStore, Long> {
    Page<DiscountStore> findAll(Pageable pageable);
    Page<DiscountStore> findAllByCategory(Pageable pageable, BusinessCategory category);
}
