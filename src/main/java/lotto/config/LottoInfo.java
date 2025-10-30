package lotto.config;

public enum LottoInfo {
    MIN_VALUE(1),
    MAX_VALUE(45),
    PICK_NUMBER_COUNT(6),
    LOTTO_PRICE(1000);

    private final int value;

    LottoInfo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
