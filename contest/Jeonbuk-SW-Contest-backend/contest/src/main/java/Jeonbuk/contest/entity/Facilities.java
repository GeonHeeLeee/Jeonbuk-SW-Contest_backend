package Jeonbuk.contest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "FacilityType")
public class Facilities {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private float latitude;

    private float longitude;

}
