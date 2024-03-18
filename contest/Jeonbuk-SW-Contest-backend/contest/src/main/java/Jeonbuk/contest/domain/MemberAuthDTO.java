package Jeonbuk.contest.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberAuthDTO {

    private String id;
    private String password;
}
