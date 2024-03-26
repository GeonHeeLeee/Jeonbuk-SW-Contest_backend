package Jeonbuk.contest.entity.safeReturn;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class CCTV extends SafeReturn {

    private String roadAddress;

    private String localAddress;

    private int cameraCount;

    private int cameraPixels;

    private String filmingInformation;

    private int storageDays;

    private LocalDate installDate;

}
