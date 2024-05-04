package Jeonbuk.contest.entity.safeReturn;


import Jeonbuk.contest.entity.enumType.MySafeReturnType;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class CCTV extends MySafeReturn {

    private String roadAddress;

    private String localAddress;

    private int cameraCount;

    private int cameraPixels;

    private String filmingInformation;

    private int storageDays;

    private LocalDate installDate;
    @Transient
    private MySafeReturnType type = MySafeReturnType.CCTV;

}
