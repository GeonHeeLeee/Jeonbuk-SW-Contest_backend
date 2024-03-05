package Jeonbuk.contest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class GoodPriceRestaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodType;

    private String storeName;

    private String ownerName;

    private String roadAddress;

    private String mainFood;

    private String mainFoodPrice;

    private String openingHours;

    private boolean isDelivery;

    private boolean isParkable;

    private String promotion;

}
