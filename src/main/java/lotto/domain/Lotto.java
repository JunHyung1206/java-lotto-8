package lotto.domain;

import lotto.config.LottoNumberRange;
import lotto.config.LottoPickNumberCount;

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
        if (numbers.size() != LottoPickNumberCount.PICK_NUMBER_COUNT.getValue()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 " + LottoPickNumberCount.PICK_NUMBER_COUNT.getValue() + "개여야 합니다.");
        }
        if (numbers.stream().anyMatch(i -> (i < LottoNumberRange.MIN_VALUE.getValue() || i > LottoNumberRange.MAX_VALUE.getValue()))) {
            throw new IllegalArgumentException("[ERROR] 모든 정수는 " + LottoNumberRange.MIN_VALUE.getValue() + " ~ " + LottoNumberRange.MAX_VALUE.getValue() + " 사이의 값으로 이루어져야 합니다.");
        }
        if (isDuplicate(numbers)) {
            throw new IllegalArgumentException("[ERROR] 중복된 정수가 있습니다.");
        }
    }

    // Debug용
    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }

    private boolean isDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        return set.size() != numbers.size();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
