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
    private static final double PERCENT = 100.0;

    public LottoResultService(LottoResultEvaluator lottoResultEvaluator) {
        this.lottoResultEvaluator = lottoResultEvaluator;
    }

    public WinningResult calculateResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (Lotto lotto : lottos) {
            WinningRank rank = lottoResultEvaluator.evaluate(lotto, winningNumbers);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return new WinningResult(result);
    }


    public ResultStatistics aggregate(WinningResult winningResult, Payment payment) {
        long prize = calculatePrize(winningResult);
        double rateOfReturn = calculateRateOfReturn(prize, payment.getValue());
        return new ResultStatistics(prize, rateOfReturn);
    }

    public long calculatePrize(WinningResult result) {
        long prize = 0L;
        for (WinningRank winningRank : WinningRank.values()) {
            prize += winningRank.getPrize() * result.getCount(winningRank);
        }
        return prize;
    }

    public double calculateRateOfReturn(long prize, int payment) {
        if (payment <= 0L) {
            return 0.0;
        }
        return (double) prize / payment * PERCENT;
    }
}
