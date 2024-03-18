package Jeonbuk.contest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Column(length = 11)
    private String phoneNumber;
    @Column(length = 11)
    private String emergencyNumber;
}
