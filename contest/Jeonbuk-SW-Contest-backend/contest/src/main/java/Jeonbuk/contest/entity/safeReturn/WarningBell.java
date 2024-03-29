package Jeonbuk.contest.entity.safeReturn;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Year;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class WarningBell extends SafeReturn {

    private String managementNumber;

    private String installPurpose;
    private String installtype;

    private String installLocation;

    private String roadAddress;

    private String linkageMethod;

    private boolean isPoliceLinked;

    private boolean isSecurityLinked;

    private boolean isManagingOfficeLinked;

    private String installYear;

    private String managingOfficeName;
    private String managingOfficePhoneNumber;
}
