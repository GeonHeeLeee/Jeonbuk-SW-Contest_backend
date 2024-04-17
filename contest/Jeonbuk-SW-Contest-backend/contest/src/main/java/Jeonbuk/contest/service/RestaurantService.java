package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.entity.enumType.Promotion;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.repository.RestaurantRepository;
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

import static Jeonbuk.contest.exception.ErrorCode.RESTAURANT_NOT_FOUND_ID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, RESTAURANT_NOT_FOUND_ID));
    }

    public ResponseEntity<Page<Restaurant>> getAllRestaurantsPage(int page) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(PageRequest.of(page, 10));
        return ResponseEntity.ok().body(restaurantPage);
    }

    public ResponseEntity<Page<Restaurant>> getRestaurantsByPromotionPage(Promotion promotionType, int page) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAllByPromotion(promotionType, PageRequest.of(page, 10));
        return ResponseEntity.ok().body(restaurantPage);
    }

    public ResponseEntity<Map<String, List<Restaurant>>> getAllRestaurantsWithinRadius(float latitude, float longitude, float radius) {
        Map<String, List<Restaurant>> response = new HashMap<>();
        response.put("content", restaurantRepository.findWithinRadius(latitude, longitude, radius));
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Map<String, List<Restaurant>>> getRestaurantsByPromotionTypeWithinRadius(float latitude,
                                                                                      float longitude,
                                                                                      float radius,
                                                                                      Promotion promotion) {
        Map<String, List<Restaurant>> response = new HashMap<>();
        response.put("content", restaurantRepository.findByPromotionWithinRadius(latitude, longitude, radius, promotion));
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Map<String, List<Restaurant>>> searchRestaurantByStoreName(String storeName) {
        List<Restaurant> restaurantList = restaurantRepository.findByStoreNameContaining(storeName);
        Map<String, List<Restaurant>> response = new HashMap<>();
        response.put("content", restaurantList);
        return ResponseEntity.ok().body(response);
    }
}
