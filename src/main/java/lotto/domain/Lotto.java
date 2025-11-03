package lotto.domain;

import lotto.config.LottoInfo;
import lotto.exception.ErrorMessages;
import lotto.exception.LottoInvalidException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null) {
            throw new LottoInvalidException(ErrorMessages.INVALID_INPUT_ERROR);
        }
        if (numbers.size() != LottoInfo.PICK_NUMBER_COUNT) {
            throw new LottoInvalidException(ErrorMessages.NOT_LOTTO_COUNT_ERROR);
        }
        if (numbers.stream().anyMatch(this::isOutOfRange)) {
            throw new LottoInvalidException(ErrorMessages.OUT_OF_RANGE_ERROR);
        }
        if (isDuplicate(numbers)) {
            throw new LottoInvalidException(ErrorMessages.DUPLICATE_ERROR);
        }
    }

    private boolean isOutOfRange(int value){
        return (value < LottoInfo.MIN_VALUE || value > LottoInfo.MAX_VALUE);
    }

    private boolean isDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        return set.size() != numbers.size();
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }
}
