package lotto.domain;

import lotto.config.LottoInfo;
import lotto.exception.ErrorMessages;
import lotto.exception.WinningNumbersInvalidException;

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
        if (isOutOfRange(value)) {
            throw new WinningNumbersInvalidException(ErrorMessages.OUT_OF_RANGE_ERROR);
        }
    }

    private boolean isOutOfRange(int value) {
        return (value < LottoInfo.MIN_VALUE || value > LottoInfo.MAX_VALUE);
    }
}
