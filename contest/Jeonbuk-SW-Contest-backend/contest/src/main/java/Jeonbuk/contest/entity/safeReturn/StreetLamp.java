package Jeonbuk.contest.entity.safeReturn;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class StreetLamp extends SafeReturn {

    private String legaldon_c; //승현이형한테 의미 물어보고 다시 작성
}
