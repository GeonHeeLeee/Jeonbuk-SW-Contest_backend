package Jeonbuk.contest.csv;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum FILE_LOCATION {
    SAFE_RETURN("csv/safeReturn/"),
    STORE("csv/store/"),
    TOWN_STROLL("csv/");
    private String location;

    FILE_LOCATION(String location) {
        this.location = location;
    }
}

