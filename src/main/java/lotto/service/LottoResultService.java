package lotto.service;

import lotto.config.LottoInfo;
import lotto.config.WinningRank;
import lotto.domain.*;
import lotto.domain.WinningNumbers;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {


    public WinningResult calculateResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (Lotto lotto : lottos) {
            WinningRank rank = new LottoMatcher().getResult(lotto, winningNumbers);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return new WinningResult(result);
    }


    public ResultStatistics aggregate(WinningResult winningResult) {
        long prize = calculatePrize(winningResult);
        double rateOfReturn = calculateRateOfReturn(winningResult);
        return new ResultStatistics(prize, rateOfReturn);
    }

    public long calculatePrize(WinningResult result) {
        long prize = 0L;
        for (WinningRank winningRank : WinningRank.values()) {
            prize += (long)winningRank.getPrize() * result.getCount(winningRank);
        }
        return prize;
    }

    public double calculateRateOfReturn(WinningResult winningResult){
        long prize = calculatePrize(winningResult);
        int totalLottoCounts = 0;
        for (WinningRank count : winningResult.getCounts().keySet()) {
            totalLottoCounts += winningResult.getCount(count);
        }

        int totalPayment = totalLottoCounts * LottoInfo.LOTTO_PRICE;
        return (double) prize / totalPayment * 100;
    }
}
