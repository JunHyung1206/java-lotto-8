package lotto.domain;

import lotto.config.LottoInfo;
import lotto.exception.ErrorMessages;
import lotto.exception.LottoValidationException;

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
        if (value < LottoInfo.MIN_VALUE || value > LottoInfo.MAX_VALUE) {
            throw new LottoValidationException(ErrorMessages.OUT_OF_RANGE_ERROR);
        }
        if (isDuplicate(value, lotto.getNumbers())) {
            throw new LottoValidationException(ErrorMessages.DUPLICATE_ERROR);
        }
    }

    private boolean isDuplicate(int value, List<Integer> numbers) {
        return numbers.contains(value);
    }
}
