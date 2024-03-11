package Jeonbuk.contest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {

    @Id
    private String Id;
    private String password;
    private String name;
    private String phoneNumber;
    private String emergencyNumber;




}
