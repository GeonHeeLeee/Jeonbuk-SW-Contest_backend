package Jeonbuk.contest.csv;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum FILE_LOCATION {
    RELATIVE_PATH("csv/safe_return"),
    BASIC_FILE_LOCATION("C:\\Users\\LeeGeonHee\\Desktop\\after_school\\24년상반기 SW개발공모전\\data\\2_old\\"),
    MODEL_RESTAURANT(BASIC_FILE_LOCATION.getLocation() + "model_restaurant.csv"),
    GOOD_PRICE_RESTAURANT(BASIC_FILE_LOCATION.getLocation() + "good_price_restaurant.csv"),
    CULTURE_NURI_CARD(BASIC_FILE_LOCATION.getLocation() + "culture_nuri_card.csv"),
    CHILD_MEAL_CARD(BASIC_FILE_LOCATION.getLocation() + "child_meal_card.csv"),
    CHILD_LIKE_CARD(BASIC_FILE_LOCATION.getLocation() + "child_like_card.csv");



    private String location;

    private FILE_LOCATION(String location) {
        this.location = location;
    }
}

