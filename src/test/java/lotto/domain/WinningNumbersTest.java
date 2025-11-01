package lotto.domain;

import lotto.exception.LottoValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningNumbersTest {
    BonusNumber bonusNumber;
    Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @ParameterizedTest
    @ValueSource(ints = {7,8,9})
    @DisplayName("보너스 번호의 입력 값이 당첨 번호와 겹치는지 확인한다.")
    void successCase(int input) {
        bonusNumber = new BonusNumber(input);
        WinningNumbers winningNumbers = new WinningNumbers(lotto, bonusNumber);

        assertThat(winningNumbers.getBonusNumber()).isEqualTo(bonusNumber);
        assertThat(winningNumbers.getMainNumbers()).isEqualTo(lotto);

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("보너스 번호의 입력 값이 당첨 번호와 겹치는지 확인한다.")
    void duplicateCase(int input) {
        bonusNumber = new BonusNumber(input);
        assertThrows(LottoValidationException.class, () -> new WinningNumbers(lotto, bonusNumber));
    }
}
