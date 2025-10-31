package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {
    BonusNumber bonusNumber;
    Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("정상적인 동작에 대한 테스트 케이스")
    void successTestCase() {
        bonusNumber = new BonusNumber(7, lotto);
        assertThat(bonusNumber.getValue()).isEqualTo(7);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, -45})
    @DisplayName("보너스 번호의 입력 값이 1~45가 아닌 케이스")
    void invalidRangeCase(int input) {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> new BonusNumber(input, lotto));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("보너스 번호의 입력 값이 당첨 번호와 겹치는")
    void duplicateCase(int input) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new BonusNumber(input, lotto));
    }
}
