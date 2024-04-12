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

    @Column(length = 500)
    private String overview;

    private String image;

    private String holiday;

    private String name;

    private String telephone;
    private String category;
    private String address;
    private String latitude;
    private String longitude;

}
