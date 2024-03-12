package Jeonbuk.contest.entity.restaurant;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "RestaurantType")
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    protected String storeName;
    protected String roadAddress;
}
