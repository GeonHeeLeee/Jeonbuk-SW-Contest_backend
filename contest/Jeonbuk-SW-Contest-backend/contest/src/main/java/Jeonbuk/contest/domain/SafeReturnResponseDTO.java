package Jeonbuk.contest.domain;

import Jeonbuk.contest.entity.safeReturn.SafeReturn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SafeReturnResponseDTO {

    private Long id;
    private String memberId;
    private String name;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

    public SafeReturnResponseDTO(SafeReturn safeReturn) {
        this.id = safeReturn.getId();
        this.memberId = safeReturn.getMember().getId();
        this.name = safeReturn.getName();
        this.startLatitude = safeReturn.getStartLatitude();
        this.startLongitude = safeReturn.getStartLongitude();
        this.endLatitude = safeReturn.getEndLatitude();
        this.endLongitude = safeReturn.getEndLongitude();
    }
}
