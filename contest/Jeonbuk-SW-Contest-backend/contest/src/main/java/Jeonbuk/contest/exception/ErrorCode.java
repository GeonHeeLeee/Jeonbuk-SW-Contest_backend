package Jeonbuk.contest.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    MEMBER_NOT_FOUND_ID("해당 Id의 사용자가 존재하지 않습니다."),
    PHONE_NUMBER_NOT_VALID("핸드폰, 비상연락망은 '-' 제외 11글자이여야 합니다.");

    private String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
