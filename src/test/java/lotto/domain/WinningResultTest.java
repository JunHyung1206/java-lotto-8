package lotto.domain;

import lotto.config.WinningRank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class WinningResultTest {
    @Test
    @DisplayName("정상적으로 상금을 반환하는지 테스트 한다.")
    void calculatePrizeTest() {
        WinningResult winningResult = new WinningResult(Map.of(
                WinningRank.FIRST, 0,
                WinningRank.SECOND, 0,
                WinningRank.THIRD, 0,
                WinningRank.FOURTH, 1,
                WinningRank.FIFTH, 2,
                WinningRank.NONE, 5
        ));
        long expectValue = 5_0000 + 5_000 * 2;

        long prize = winningResult.calculatePrize();
        Assertions.assertThat(prize).isEqualTo(expectValue);
    }


    @Test
    @DisplayName("1등이 여러 장인 경우를 테스트한다.")
    void dreamPrizeTest() {
        WinningResult dreamWinningResult = new WinningResult(Map.of(
                WinningRank.FIRST, 20,
                WinningRank.SECOND, 0,
                WinningRank.THIRD, 0,
                WinningRank.FOURTH,0,
                WinningRank.FIFTH, 0,
                WinningRank.NONE, 0
        ));
        long expectValue = 2_000_000_000 * 20L;

        long prize = dreamWinningResult.calculatePrize();
        Assertions.assertThat(prize).isEqualTo(expectValue);
    }

}