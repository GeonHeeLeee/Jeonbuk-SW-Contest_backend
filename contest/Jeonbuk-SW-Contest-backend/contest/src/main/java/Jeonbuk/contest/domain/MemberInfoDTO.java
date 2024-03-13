package Jeonbuk.contest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberInfoDTO {

    private String id;
    private String name;
    private String phoneNumber;
    private String emergencyNumber;
}
