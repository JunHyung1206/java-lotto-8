package lotto.config;

public enum LottoNumberRange {
    MIN_VALUE(1), MAX_VALUE(45);

    private final int value;

    LottoNumberRange(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
