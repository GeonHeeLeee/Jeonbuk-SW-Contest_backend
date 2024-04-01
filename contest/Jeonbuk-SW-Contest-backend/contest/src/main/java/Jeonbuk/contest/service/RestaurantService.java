package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.PROMOTION_TYPE;
import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<Page<Restaurant>> getAllRestaurants(int page) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(PageRequest.of(page, 10));
        return ResponseEntity.ok().body(restaurantPage);
    }

    public ResponseEntity<Page<Restaurant>> getGoodPriceRestaurants(PROMOTION_TYPE promotionType, int page) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAllByPromotionType(promotionType, PageRequest.of(page, 10));
        return ResponseEntity.ok().body(restaurantPage);
    }
}
