package Jeonbuk.contest.entity.restaurant.type;

import Jeonbuk.contest.entity.restaurant.Restaurant;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class ChildLikeCard extends Restaurant {

    private String businessType;

    private String phoneNumber;
    private String promotion;


}
