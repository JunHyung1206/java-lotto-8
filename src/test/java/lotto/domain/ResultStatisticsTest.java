package lotto.domain;

import lotto.config.WinningRank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;


class ResultStatisticsTest {
    @Test
    @DisplayName("정상적인 수익률을 반환한다.")
    void returnOfRateTest() {
        WinningResult winningResult = new WinningResult(Map.of(
                WinningRank.FIRST, 0,
                WinningRank.SECOND, 0,
                WinningRank.THIRD, 0,
                WinningRank.FOURTH, 1,
                WinningRank.FIFTH, 2,
                WinningRank.NONE, 5
        ));

        ResultStatistics resultStatistics = ResultStatistics.of(winningResult);
        double rateOfReturn = resultStatistics.getRateOfReturn();
        double expectedValue = (double)(50000 + 5000*2)/8000 * 100;
        Assertions.assertThat(rateOfReturn).isEqualTo(expectedValue);
    }


    @Test
    @DisplayName("만약 한장도 구매를 안했다면 0을 반환한다.")
    void notPurchasedTest() {
        WinningResult winningResult = new WinningResult(Map.of(
                WinningRank.FIRST, 0,
                WinningRank.SECOND, 0,
                WinningRank.THIRD, 0,
                WinningRank.FOURTH, 0,
                WinningRank.FIFTH, 0,
                WinningRank.NONE, 0
        ));

        ResultStatistics resultStatistics = ResultStatistics.of(winningResult);
        double rateOfReturn = resultStatistics.getRateOfReturn();
        double expectedValue = 0.0;
        Assertions.assertThat(rateOfReturn).isEqualTo(expectedValue);
    }
}
