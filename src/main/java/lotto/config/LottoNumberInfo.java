package lotto.config;

public enum LottoNumberInfo {
    MIN_VALUE(1),
    MAX_VALUE(45),
    PICK_NUMBER_COUNT(6);

    private final int value;

    LottoNumberInfo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
