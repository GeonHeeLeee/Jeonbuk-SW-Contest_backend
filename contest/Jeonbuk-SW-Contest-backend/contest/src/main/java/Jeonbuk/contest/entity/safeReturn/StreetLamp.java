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
public class StreetLamp extends SafeReturn {

    private String administrativeCode;
    @Transient
    private SafeReturnType type = SafeReturnType.STREET_LAMP;
}
