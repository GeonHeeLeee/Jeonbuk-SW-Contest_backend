package Jeonbuk.contest.entity.safeReturn;

import Jeonbuk.contest.entity.enumType.SafeReturnType;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    @Transient
    private SafeReturnType type = SafeReturnType.WARNING_BELL;
}
