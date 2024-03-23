package Jeonbuk.contest.entity.safeReturn;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "FacilityType")
public class SafeReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private float latitude;

    private float longitude;

}
