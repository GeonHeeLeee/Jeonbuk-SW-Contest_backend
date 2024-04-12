package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.Restaurant;
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

import java.util.HashMap;
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

    public ResponseEntity<Map<String, List<DiscountStore>>> getAllDiscountStoreWithinRadius(float latitude, float longitude, float radius) {
        Map<String, List<DiscountStore>> response = new HashMap<>();
        response.put("content", discountStoreRepository.findWithinRadius(latitude, longitude, radius));
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Map<String, List<DiscountStore>>> getDiscountStoreByCategoryWithinRadius(float latitude, float longitude, float radius, BusinessCategory category) {
        Map<String, List<DiscountStore>> response = new HashMap<>();
        response.put("content", discountStoreRepository.findByCategoryWithinRadius(latitude, longitude, radius, category));
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<List<DiscountStore>> searchDiscountStoreByStoreName(String storeName) {
        List<DiscountStore> restaurantList = discountStoreRepository.findByStoreNameContaining(storeName);
        return ResponseEntity.ok().body(restaurantList);
    }
}
