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
public class Model extends Restaurant {

    private String locationAddress; //소재지 주소

    private String foodType;

    private String mainFood;

    private String phoneNumber;

}
