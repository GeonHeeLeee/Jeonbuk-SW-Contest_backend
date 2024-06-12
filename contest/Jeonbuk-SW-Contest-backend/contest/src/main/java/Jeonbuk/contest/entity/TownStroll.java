package Jeonbuk.contest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TownStroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String middleCategory;

    private String smallCategory;

    private String address;
    private float latitude;
    private float longitude;

}
