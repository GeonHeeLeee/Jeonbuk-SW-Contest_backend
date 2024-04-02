package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.enumType.BusinessCategory;
import Jeonbuk.contest.repository.DiscountStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountStoreService {

    private final DiscountStoreRepository discountStoreRepository;

    public ResponseEntity<Page<DiscountStore>> getAllDiscountStoreForList(int page) {
        Page<DiscountStore> discountStorePage = discountStoreRepository.findAll(PageRequest.of(page, 10));
        return ResponseEntity.ok().body(discountStorePage);
    }

    public ResponseEntity<Page<DiscountStore>> getDiscountStoreByCategoryForList(int page, BusinessCategory category) {
        Page<DiscountStore> discountStorePage = discountStoreRepository.findAllByCategory(PageRequest.of(page, 10), category);
        return ResponseEntity.ok().body(discountStorePage);
    }
}
