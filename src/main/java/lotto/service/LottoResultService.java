package lotto.service;

import lotto.config.LottoInfo;
import lotto.config.WinningRank;
import lotto.domain.*;
import lotto.domain.WinningNumbers;
import lotto.domain.lottoresultevaluator.LottoResultEvaluator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {
    private final LottoResultEvaluator lottoResultEvaluator;

    public LottoResultService(LottoResultEvaluator lottoResultEvaluator) {
        this.lottoResultEvaluator = lottoResultEvaluator;
    }

    public WinningResult calculateResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (Lotto lotto : lottos) {
            WinningRank rank = lottoResultEvaluator.evaluate(lotto, winningNumbers);
            result.merge(rank, 1, Integer::sum);
        }
        return new WinningResult(result);
    }

    public ResultStatistics aggregate(WinningResult winningResult) {
        return ResultStatistics.of(winningResult);
    }
}
