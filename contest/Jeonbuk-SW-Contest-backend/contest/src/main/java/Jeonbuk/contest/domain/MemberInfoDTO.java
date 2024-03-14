package Jeonbuk.contest.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@Builder
public class MemberInfoDTO {

    private String id;
    private String name;
    private String phoneNumber;
    private String emergencyNumber;
}
