package Jeonbuk.contest.domain;

import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberInfoDTO {

    private String id;
    private String name;
    @Size(min = 8, max = 8, message = "전화번호는 '-'을 제외한 8자리여야 합니다")
    private String phoneNumber;
    @Size(min = 8, max = 8, message = "전화번호는 '-'을 제외한 8자리여야 합니다")
    private String emergencyNumber;
}
