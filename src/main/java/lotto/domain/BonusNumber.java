package lotto.domain;

import lotto.config.LottoNumberInfo;
import lotto.exception.ErrorMessages;

import java.util.List;


public class BonusNumber {
    private final int value;

    public BonusNumber(int value, Lotto lotto) {
        validate(value, lotto);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value, Lotto lotto) {
        if (value < LottoNumberInfo.MIN_VALUE.getValue() || value > LottoNumberInfo.MAX_VALUE.getValue()) {
            throw new IllegalArgumentException(ErrorMessages.OUT_OF_RANGE_ERROR.getMessage());
        }
        if (isDuplicate(value, lotto.getNumbers())) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_ERROR.getMessage());
        }
    }

    private boolean isDuplicate(int value, List<Integer> numbers) {
        return numbers.contains(value);
    }
}
