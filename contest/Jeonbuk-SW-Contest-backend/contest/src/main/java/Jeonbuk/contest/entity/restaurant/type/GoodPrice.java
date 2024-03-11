package Jeonbuk.contest.entity.restaurant.type;

import Jeonbuk.contest.entity.restaurant.Restaurant;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class GoodPrice extends Restaurant {

    private String foodType;

    private String ownerName;

    private String mainFood;

    private String mainFoodPrice;

    private String openingHours;

    private boolean supportsDelivery;

    private boolean isParkable;

    private String promotion;

}