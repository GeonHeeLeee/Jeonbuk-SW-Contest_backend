package Jeonbuk.contest.csv;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum FILE_LOCATION {
    RELATIVE_PATH("csv/safe_return/");

    private String location;

    FILE_LOCATION(String location) {
        this.location = location;
    }
}

