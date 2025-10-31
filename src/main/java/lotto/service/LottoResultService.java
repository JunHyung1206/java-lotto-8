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
            WinningRank rank = new LottoResult(lotto, winningNumbers).getResult();
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return new WinningResult(result);
    }


    public ResultStatistics aggregate(WinningResult winningResult) {
        int prize = calculatePrize(winningResult);
        double rateOfReturn = calculateRateOfReturn(winningResult);
        return new ResultStatistics(prize, rateOfReturn);
    }

    public int calculatePrize(WinningResult result) {
        int prize = 0;
        for (WinningRank winningRank : WinningRank.values()) {
            prize += winningRank.getPrize() * result.getCount(winningRank);
        }
        return prize;
    }

    public double calculateRateOfReturn(WinningResult winningResult){
        int prize = calculatePrize(winningResult);
        int totalLottoCounts = 0;
        for (WinningRank count : winningResult.getCounts().keySet()) {
            totalLottoCounts += winningResult.getCount(count);
        }

        int totalPayment = totalLottoCounts * LottoInfo.LOTTO_PRICE;
        return (double) prize / totalPayment * 100;
    }
}
