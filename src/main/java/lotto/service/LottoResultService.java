package lotto.service;

import lotto.config.LottoInfo;
import lotto.config.WinningRank;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.ResultStatistics;
import lotto.domain.WinningNumbers;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {
    public ResultStatistics aggregate(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<WinningRank, Integer> result = aggregateResult(lottos, winningNumbers);
        int prize = calculatePrize(result);
        double rateOfReturn = calculateRateOfReturn(result, lottos);
        return new ResultStatistics(result, prize, rateOfReturn);
    }

    private Map<WinningRank, Integer> aggregateResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (Lotto lotto : lottos) {
            WinningRank rank = new LottoResult(lotto, winningNumbers).getResult();
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }

    public int calculatePrize(Map<WinningRank, Integer> result) {
        int prize = 0;
        for (WinningRank winningRank : result.keySet()) {
            prize += winningRank.getPrize() * result.get(winningRank);
        }
        return prize;
    }

    public double calculateRateOfReturn(Map<WinningRank, Integer> result, List<Lotto> lottos){
        int prize = calculatePrize(result);
        int totalPayment = lottos.size() * LottoInfo.LOTTO_PRICE;
        return (double) prize / totalPayment * 100;
    }
}
