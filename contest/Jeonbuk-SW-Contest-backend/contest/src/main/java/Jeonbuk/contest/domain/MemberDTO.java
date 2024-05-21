package Jeonbuk.contest.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자")
public class MemberDTO {
    @Schema(description = "사용자 ID - 최소 6자 이상")
    private String id;
    @Schema(description = "사용자 비밀번호 - 최소 6자 이상")
    private String password;
    @Schema(description = "사용자 이름")
    private String name;
    @Schema(description = "사용자 핸드폰 번호 - 01012341234")
    private String phoneNumber;
    @Schema(description = "긴급 전화번호 - 01012341234")
    private String emergencyNumber;
}
