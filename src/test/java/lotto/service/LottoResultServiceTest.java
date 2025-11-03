package lotto.service;

import lotto.domain.WinningRank;
import lotto.domain.*;
import lotto.service.lottogenerator.LottoGenerator;
import lotto.service.lottoresultevaluator.LottoResultEvaluatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultServiceTest {

    LottoPurchaseService lottoPurchaseService;
    LottoResultService lottoResultService;
    WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));
    @BeforeEach
    void setUp() {
        List<List<Integer>> testSet = List.of(
                List.of(1, 2, 3, 4, 5, 6),  // 1등
                List.of(1, 6, 3, 4, 5, 2),  // 1등
                List.of(1, 2, 3, 4, 5, 7),  // 2등
                List.of(1, 2, 3, 4, 5, 8),  // 3등
                List.of(1, 45, 34, 6, 24, 10),  // 등수 없음
                List.of(45, 21, 3, 24, 5, 6),  // 5등
                List.of(1, 2, 3, 4, 5, 6),  // 1등
                List.of(1, 2, 3, 4, 10, 11)  // 4등
        );
        lottoPurchaseService = new LottoPurchaseService(new ManualLottoGenerator(testSet));
    }

    @Test
    @DisplayName("각 구매액수에 맞게 로또를 생성한다.")
    void successCase() {
        // given
        List<Lotto> lottos = lottoPurchaseService.purchase(new Payment(8000));

        // when
        lottoResultService = new LottoResultService(new LottoResultEvaluatorImpl());
        lottoResultService.calculateResult(lottos, winningNumbers);
        WinningResult winningResult = lottoResultService.calculateResult(lottos, winningNumbers);
        ResultStatistics aggregate = lottoResultService.aggregate(winningResult);

        // then
        long expectPrize = WinningRank.FIRST.getPrize() * 3 + WinningRank.SECOND.getPrize() + WinningRank.THIRD.getPrize() + WinningRank.FOURTH.getPrize() + WinningRank.FIFTH.getPrize();
        assertThat(winningResult.getCount(WinningRank.FIRST)).isEqualTo(3);
        assertThat(aggregate.getPrize()).isEqualTo(expectPrize);
        assertThat(aggregate.getRateOfReturn()).isEqualTo((double) expectPrize / 8000 * 100);
    }

    static class ManualLottoGenerator implements LottoGenerator {
        private final List<List<Integer>> lottos;
        private int idx = 0;

        public ManualLottoGenerator(List<List<Integer>> lottos) {
            this.lottos = lottos;
        }

        @Override
        public Lotto generate() {
            List<Integer> pick = lottos.get(idx % lottos.size());
            idx++;
            return new Lotto(pick);
        }
    }

}
