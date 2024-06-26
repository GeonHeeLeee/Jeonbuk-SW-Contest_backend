package Jeonbuk.contest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Festival {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String subtitle;
    private String title;
    private String schedule;
    @Column(length = 1000)
    private String content;
    @Column(length = 1000)
    private String image;
    private String address;
    private float latitude;
    private float longitude;
}
