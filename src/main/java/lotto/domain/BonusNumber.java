package lotto.domain;

import lotto.config.LottoInfo;
import lotto.exception.ErrorMessages;
import lotto.exception.LottoValidationException;

public class BonusNumber {
    private final int value;

    public BonusNumber(int value) {
        validate(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value) {
        if (value < LottoInfo.MIN_VALUE || value > LottoInfo.MAX_VALUE) {
            throw new LottoValidationException(ErrorMessages.OUT_OF_RANGE_ERROR);
        }
    }
}
