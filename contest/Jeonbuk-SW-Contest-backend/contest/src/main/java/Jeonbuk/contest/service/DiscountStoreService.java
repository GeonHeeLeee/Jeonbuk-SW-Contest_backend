package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.enumType.BusinessCategory;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.repository.DiscountStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Jeonbuk.contest.exception.ErrorCode.DISCOUNT_STORE_NOT_FOUND_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountStoreService {

    private final DiscountStoreRepository discountStoreRepository;

    public DiscountStore findById(Long id) {
        return discountStoreRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, DISCOUNT_STORE_NOT_FOUND_ID));
    }

    public ResponseEntity<Page<DiscountStore>> getAllDiscountStorePage(int page) {
        Page<DiscountStore> discountStorePage = discountStoreRepository.findAll(PageRequest.of(page, 10));
        return ResponseEntity.ok().body(discountStorePage);
    }

    public ResponseEntity<Page<DiscountStore>> getDiscountStoreByCategoryPage(int page, BusinessCategory category) {
        Page<DiscountStore> discountStorePage = discountStoreRepository.findAllByCategory(PageRequest.of(page, 10), category);
        return ResponseEntity.ok().body(discountStorePage);
    }

    public ResponseEntity<Map<BusinessCategory, List<DiscountStore>>> getAllDiscountStoreWithinRadius(float latitude, float longitude, float radius) {
        Map<BusinessCategory, List<DiscountStore>> discountStoreMap
                = discountStoreRepository.findWithinRadius(latitude, longitude, radius)
                .stream().collect(Collectors.groupingBy(DiscountStore::getCategory));
        return ResponseEntity.ok().body(discountStoreMap);
    }

    public ResponseEntity<List<DiscountStore>> getDiscountStoreByCategoryWithinRadius(float latitude, float longitude, float radius, BusinessCategory category) {
        return ResponseEntity.ok().body(discountStoreRepository.findByCategoryWithinRadius(latitude, longitude, radius, category));
    }
}
