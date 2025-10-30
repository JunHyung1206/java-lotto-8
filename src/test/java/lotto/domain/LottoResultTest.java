package lotto.domain;

import lotto.config.WinningRank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class LottoResultTest {
    LottoResult lottoResult;
    WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7, winningLotto);
        winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
    }

    @Test
    void successTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        lottoResult = new LottoResult(lotto, winningNumbers);
        WinningRank result = lottoResult.getResult();
        Assertions.assertThat(result).isInstanceOf(WinningRank.class);
        Assertions.assertThat(result).isEqualTo(WinningRank.FIRST);
    }
}