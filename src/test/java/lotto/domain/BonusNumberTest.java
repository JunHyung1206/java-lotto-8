package lotto.domain;

import lotto.exception.LottoValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {
    BonusNumber bonusNumber;

    @Test
    @DisplayName("정상적인 동작에 대한 테스트 케이스")
    void successTestCase() {
        bonusNumber = new BonusNumber(7);
        assertThat(bonusNumber.getValue()).isEqualTo(7);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, -45})
    @DisplayName("보너스 번호의 입력 값이 1 ~ 45가 아닌 케이스")
    void invalidRangeCase(int input) {
        assertThrows(LottoValidationException.class, () -> new BonusNumber(input));
    }
}
