package Jeonbuk.contest.entity.safeReturn;

import Jeonbuk.contest.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "memberId")
    private Member member;

    private String name;

    private float startLatitude;
    private float startLongitude;
    private float endLatitude;
    private float endLongitude;
}
