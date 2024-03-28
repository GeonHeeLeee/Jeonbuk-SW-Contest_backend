package Jeonbuk.contest.entity;

public enum RESTAURANT_TYPE {
    GOOD_PRICE("착한가격업소"),
    CHILD_LIKE("아이조아카드"),
    CHILD_MEAL("아동급식카드"),
    MODEL("모범음식점");

    private String value;

    RESTAURANT_TYPE(String value) {
        this.value = value;
    }

    public static RESTAURANT_TYPE converte(String input) {
        for (RESTAURANT_TYPE type : RESTAURANT_TYPE.values()) {
            if (type.value.equals(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("알 수 없는 RESTAURANT_TYPE: {}" + input);
    }


}
