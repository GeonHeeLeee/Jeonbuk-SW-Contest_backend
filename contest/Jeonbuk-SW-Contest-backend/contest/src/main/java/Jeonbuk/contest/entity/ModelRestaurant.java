package Jeonbuk.contest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ModelRestaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;

    private String roadAddress; //도로명 주소

    private String locationAddress; //소재지 주소

    private String foodType;

    private String mainFood;

    private String phoneNumber;
}
