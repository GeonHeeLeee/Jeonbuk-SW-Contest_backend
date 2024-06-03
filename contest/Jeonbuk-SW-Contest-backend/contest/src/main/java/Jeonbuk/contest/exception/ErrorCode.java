package Jeonbuk.contest.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    MEMBER_NOT_FOUND_ID("해당 Id의 사용자가 존재하지 않습니다."),
    PHONE_NUMBER_NOT_VALID("핸드폰, 비상연락망은 '-' 제외 11글자이여야 합니다."),
    DISCOUNT_STORE_NOT_FOUND_ID("해당 Id의 할인매장이 존재하지 않습니다."),

    RESTAURANT_NOT_FOUND_ID("해당 Id의 레스토랑이 존재하지 않습니다."),
    BOOKMARK_NOT_FOUND_ID("해당 Id의 북마크가 존재하지 않습니다."),
    BOOKMARK_ALREADY_EXISTS("해당 북마크는 이미 존재합니다."),
    BOOKMARK_TYPE_UNSUPPORTED("지원되지 않은 북마크 Type 입니다."),
    FESTIVAL_NOT_FOUND_ID("해당 Id의 축제가 존재하지 않습니다."),
    TOWN_STROLL_NOT_FOUND_ID("해당 Id의 동네 마실이 존재하지 않습니다"),
    MY_SAFE_RETURN_TYPE_NOT_VALID("해당 타입의 내 안심귀가는 존재하지 않습니다."),
    RETURN_ROUTE_NOT_FOUND_ID("해당 Id의 귀가 경로가 존재하지 않습니다.");
    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
