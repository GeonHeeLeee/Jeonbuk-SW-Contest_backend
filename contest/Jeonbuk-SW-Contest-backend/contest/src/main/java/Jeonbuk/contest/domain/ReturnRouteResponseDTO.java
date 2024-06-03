package Jeonbuk.contest.domain;

import Jeonbuk.contest.entity.safeReturn.ReturnRoute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRouteResponseDTO {

    private Long id;
    private String memberId;
    private String name;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

    public ReturnRouteResponseDTO(ReturnRoute returnRoute) {
        this.id = returnRoute.getId();
        this.memberId = returnRoute.getMember().getId();
        this.name = returnRoute.getName();
        this.startLatitude = returnRoute.getStartLatitude();
        this.startLongitude = returnRoute.getStartLongitude();
        this.endLatitude = returnRoute.getEndLatitude();
        this.endLongitude = returnRoute.getEndLongitude();
    }
}
