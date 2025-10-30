package lotto.domain;

import lotto.config.LottoNumberInfo;
import lotto.exception.ErrorMessages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        numbers = new ArrayList<>(numbers);
        numbers.sort(Integer::compareTo);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoNumberInfo.PICK_NUMBER_COUNT.getValue()) {
            throw new IllegalArgumentException(ErrorMessages.NOT_LOTTO_COUNT_ERROR.getMessage());
        }
        if (numbers.stream().anyMatch(i -> (i < LottoNumberInfo.MIN_VALUE.getValue() || i > LottoNumberInfo.MAX_VALUE.getValue()))) {
            throw new IllegalArgumentException(ErrorMessages.OUT_OF_RANGE_ERROR.getMessage());
        }
        if (isDuplicate(numbers)) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_ERROR.getMessage());
        }
    }

    private boolean isDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        return set.size() != numbers.size();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
