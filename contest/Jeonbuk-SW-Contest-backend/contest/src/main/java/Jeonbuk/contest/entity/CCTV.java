package Jeonbuk.contest.entity;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class CCTV extends Facilities {

    private String roadAddress;

    private String roadNumber;

    private Integer cameraCount;

    private Integer cameraPixels;

    private String filmingInformation;

    private Integer storageDays;

    private Date installationDate;

}
