package Jeonbuk.contest.entity.restaurant.type;

import Jeonbuk.contest.entity.restaurant.Restaurant;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class ChildLikeCard extends Restaurant {

    private String businessType;

    private String phoneNumber;
    private String promotion;


}
