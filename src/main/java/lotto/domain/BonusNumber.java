package lotto.domain;

import lotto.config.LottoNumberRange;
import lotto.config.LottoPickNumberCount;

import java.util.List;

public class BonusNumber {
    private int value;
    public BonusNumber(int value, Lotto lotto) {
        validate(value, lotto);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value, Lotto lotto) {
        if (value < LottoNumberRange.MIN_VALUE.getValue() || value > LottoNumberRange.MAX_VALUE.getValue()) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 " + LottoNumberRange.MIN_VALUE.getValue() + " ~ " + LottoNumberRange.MAX_VALUE.getValue() + " 사이의 값이여야 합니다.");
        }
        if (isDuplicate(value, lotto.getNumbers())) {
            throw new IllegalArgumentException("[ERROR] 중복된 정수가 있습니다.");
        }
    }

    private boolean isDuplicate(int value, List<Integer> numbers) {
        return numbers.contains(value);
    }

    // 디버깅용
    @Override
    public String toString() {
        return "BonusNumber{" +
                "value=" + value +
                '}';
    }
}
