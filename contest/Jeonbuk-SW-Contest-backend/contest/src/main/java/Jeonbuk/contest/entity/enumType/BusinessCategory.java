package Jeonbuk.contest.entity.enumType;


public enum BusinessCategory {
    LEISURE("여가/레저"),
    SERVICES("서비스업"),
    FOOD("음식"),
    GOODS("잡화"),
    MISC("기타"),
    FOOD_BEVERAGE("식품/음료"),
    BOOKS_STATIONERY("도서/문구"),
    RETAIL("도소매"),
    EDUCATION("교육"),
    AUTOMOTIVE("자동차/주유");

    private final String value;

    BusinessCategory(String description) {
        this.value = description;
    }
    public static BusinessCategory convert(String input) {
        for (BusinessCategory type : BusinessCategory.values()) {
            if (type.value.equals(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("알 수 없는 BusinessCategory: " + input);
    }
}
