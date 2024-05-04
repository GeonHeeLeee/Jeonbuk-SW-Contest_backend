package Jeonbuk.contest.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SafeReturnDTO {

    private Long id;
    private String memberId;
    private String name;
    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;
}
