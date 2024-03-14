package Jeonbuk.contest.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberRegisterDTO {

    private String id;
    private String password;
}
