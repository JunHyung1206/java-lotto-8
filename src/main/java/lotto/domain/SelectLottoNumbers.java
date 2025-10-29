package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SelectLottoNumbers {
    private final List<Integer> numbers;

    public SelectLottoNumbers(String rawNumbers) {
        validate(rawNumbers);
        this.numbers = toIntegerList(rawNumbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(String rawNumbers) {
        List<Integer> numbers;
        try{
            numbers = toIntegerList(rawNumbers);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력된 형식이 올바르지 않습니다. 숫자와 숫자 사이는 ,로 구분합니다.");
        }
        
        validateLottoRules(numbers);
    }

    private void validateLottoRules(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 입력 받은 정수는 6개여야 합니다.");
        }
        if (numbers.stream().anyMatch(i -> (i < 0 || i > 45))) {
            throw new IllegalArgumentException("[ERROR] 모든 정수는 1~45로 이루어져야 합니다.");
        }
        if (isDuplicate(numbers)){
            throw new IllegalArgumentException("[ERROR] 중복된 정수가 있습니다.");
        }
    }

    private boolean isDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        return set.size() != numbers.size();
    }

    private List<Integer> toIntegerList(String rawNumbers) {
        return Arrays.stream(rawNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

}
