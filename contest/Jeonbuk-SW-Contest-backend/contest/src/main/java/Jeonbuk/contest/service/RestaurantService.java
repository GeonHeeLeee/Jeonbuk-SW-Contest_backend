package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.Restaurant;
import Jeonbuk.contest.entity.enumType.Promotion;
import Jeonbuk.contest.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<Page<Restaurant>> getAllRestaurantsPage(int page) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(PageRequest.of(page, 10));
        return ResponseEntity.ok().body(restaurantPage);
    }

    public ResponseEntity<Page<Restaurant>> getRestaurantsByPromotionPage(Promotion promotionType, int page) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAllByPromotionType(promotionType, PageRequest.of(page, 10));
        return ResponseEntity.ok().body(restaurantPage);
    }

    public ResponseEntity<Map<Promotion, List<Restaurant>>> getAllRestaurantsWithinRadius(float latitude, float longitude, float radius) {
        Map<Promotion, List<Restaurant>> restaurantMap =
                restaurantRepository.findWithinRadius(latitude, longitude, radius)
                        .stream().collect(Collectors.groupingBy(Restaurant::getPromotionType));
        return ResponseEntity.ok().body(restaurantMap);
    }

    public ResponseEntity<List<Restaurant>> getRestaurantsByPromotionTypeWithinRadius(float latitude,
                                                                                      float longitude,
                                                                                      float radius,
                                                                                      Promotion promotionType) {
        List<Restaurant> restaurantList = restaurantRepository.findByPromotionWithinRadius(latitude, longitude, radius, promotionType);
        return ResponseEntity.ok().body(restaurantList);
    }
}
