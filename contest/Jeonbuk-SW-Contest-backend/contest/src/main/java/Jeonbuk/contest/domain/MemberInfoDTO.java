package Jeonbuk.contest.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberInfoDTO {

    private String id;
    private String name;
    private String phoneNumber;
    private String emergencyNumber;
}
