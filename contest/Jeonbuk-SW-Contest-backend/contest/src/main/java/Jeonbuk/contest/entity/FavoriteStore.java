package Jeonbuk.contest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FavoriteStore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private String storeType;

    
}
