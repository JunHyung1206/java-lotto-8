package lotto.util;

import java.util.Arrays;
import java.util.List;

public class NumbersParser {
    private NumbersParser() {
    }

    public static List<Integer> toIntegerList(String rawNumbers) {
        return Arrays.stream(rawNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
