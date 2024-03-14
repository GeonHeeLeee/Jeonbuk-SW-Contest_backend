package Jeonbuk.contest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    private String id;
    private String password;
    private String name;

    @Size(min = 8, max = 8, message = "전화번호는 '-'을 제외한 8자리여야 합니다")
    private String phoneNumber;
    @Size(min = 8, max = 8, message = "전화번호는 '-'을 제외한 8자리여야 합니다")
    private String emergencyNumber;
}
