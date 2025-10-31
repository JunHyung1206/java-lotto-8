package lotto.domain;

import lotto.config.LottoInfo;
import lotto.exception.ErrorMessages;
import lotto.exception.LottoValidationException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoInfo.PICK_NUMBER_COUNT.getValue()) {
            throw new LottoValidationException(ErrorMessages.NOT_LOTTO_COUNT_ERROR);
        }
        if (numbers.stream().anyMatch(lottoNumber -> (lottoNumber < LottoInfo.MIN_VALUE.getValue() || lottoNumber > LottoInfo.MAX_VALUE.getValue()))) {
            throw new LottoValidationException(ErrorMessages.OUT_OF_RANGE_ERROR);
        }
        if (isDuplicate(numbers)) {
            throw new LottoValidationException(ErrorMessages.DUPLICATE_ERROR);
        }
    }

    private boolean isDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        return set.size() != numbers.size();
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }
}
