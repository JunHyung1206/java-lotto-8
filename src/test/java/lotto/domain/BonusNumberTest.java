package lotto.domain;

import lotto.exception.WinningNumbersInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 15, 45})
    @DisplayName("정상적인 동작에 대한 테스트 케이스")
    void successTestCase(int input) {
        assertThat(new BonusNumber(input).getValue()).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, 100, -1})
    @DisplayName("보너스 번호의 입력 값이 1 ~ 45가 아닌 케이스")
    void invalidRangeCase(int input) {
        assertThrows(WinningNumbersInvalidException.class, () -> new BonusNumber(input));
    }
}
