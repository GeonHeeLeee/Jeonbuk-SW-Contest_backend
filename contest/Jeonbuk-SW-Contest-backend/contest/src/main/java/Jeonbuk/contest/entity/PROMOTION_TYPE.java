package Jeonbuk.contest.entity;

public enum PROMOTION_TYPE {
    GOOD_PRICE("착한가격업소"),
    CHILD_LIKE("아이조아카드"),
    CHILD_MEAL("아동급식카드"),
    MODEL("모범음식점"),
    CULTURE_NURI("문화누리카드");

    private String value;

    PROMOTION_TYPE(String value) {
        this.value = value;
    }

    public static PROMOTION_TYPE converte(String input) {
        for (PROMOTION_TYPE type : PROMOTION_TYPE.values()) {
            if (type.value.equals(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("알 수 없는 RESTAURANT_TYPE: {}" + input);
    }


}
