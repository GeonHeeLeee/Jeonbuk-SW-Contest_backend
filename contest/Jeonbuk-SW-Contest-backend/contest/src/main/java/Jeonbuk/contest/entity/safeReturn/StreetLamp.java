package Jeonbuk.contest.entity.safeReturn;

import Jeonbuk.contest.entity.enumType.MySafeReturnType;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class StreetLamp extends MySafeReturn {

    private String administrativeCode;
    @Transient
    private MySafeReturnType type = MySafeReturnType.STREET_LAMP;
}
