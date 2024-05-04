package Jeonbuk.contest.domain;


import Jeonbuk.contest.entity.safeReturn.SafeReturn;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SafeReturnRequestDTO {

    private String memberId;
    private String name;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

    public SafeReturnRequestDTO(SafeReturn safeReturn) {
        this.memberId = safeReturn.getMember().getId();
        this.name = safeReturn.getName();
        this.startLatitude = safeReturn.getStartLatitude();
        this.startLongitude = safeReturn.getStartLongitude();
        this.endLatitude = safeReturn.getEndLatitude();
        this.endLongitude = safeReturn.getEndLongitude();
    }
}
