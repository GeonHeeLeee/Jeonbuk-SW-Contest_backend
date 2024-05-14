package Jeonbuk.contest.entity.enumType;

public enum Promotion {
    GOOD_PRICE("착한가격업소"),
    CHILD_LIKE("아이조아카드"),
    CHILD_MEAL("아동급식카드"),
    MODEL("모범음식점"),
    CULTURE_NURI_CARD("문화누리카드");

    private String value;

    Promotion(String value) {
        this.value = value;
    }

    public static Promotion convert(String input) {
        for (Promotion type : Promotion.values()) {
            if (type.value.equals(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("알 수 없는 PromotionType: " + input);
    }


}
