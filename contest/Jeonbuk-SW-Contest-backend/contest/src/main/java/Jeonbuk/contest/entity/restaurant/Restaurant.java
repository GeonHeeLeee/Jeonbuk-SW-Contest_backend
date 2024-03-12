package Jeonbuk.contest.entity.restaurant;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "RestaurantType")
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String storeName;
    private String roadAddress;
}
