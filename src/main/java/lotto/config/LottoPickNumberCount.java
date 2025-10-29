package lotto.config;

public enum LottoPickNumberCount {
    PICK_NUMBER_COUNT(6);

    private final int value;

    LottoPickNumberCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
