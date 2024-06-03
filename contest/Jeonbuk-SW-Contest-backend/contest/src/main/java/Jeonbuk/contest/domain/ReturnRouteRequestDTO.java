package Jeonbuk.contest.domain;


import Jeonbuk.contest.entity.safeReturn.ReturnRoute;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRouteRequestDTO {

    private String memberId;
    private String name;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;

    public ReturnRouteRequestDTO(ReturnRoute returnRoute) {
        this.memberId = returnRoute.getMember().getId();
        this.name = returnRoute.getName();
        this.startLatitude = returnRoute.getStartLatitude();
        this.startLongitude = returnRoute.getStartLongitude();
        this.endLatitude = returnRoute.getEndLatitude();
        this.endLongitude = returnRoute.getEndLongitude();
    }
}
