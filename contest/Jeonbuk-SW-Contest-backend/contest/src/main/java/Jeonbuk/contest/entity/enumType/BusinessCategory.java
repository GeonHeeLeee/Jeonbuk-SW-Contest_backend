package Jeonbuk.contest.entity.enumType;


public enum BusinessCategory {
    LEISURE("여가/레저"),
    SERVICES("서비스업"),
    GOODS("잡화"),
    ETC("기타"),
    EDUCATION("교육"),
    LIFE("생활");

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
