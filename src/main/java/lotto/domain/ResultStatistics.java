package lotto.domain;

import lotto.config.LottoInfo;
import lotto.config.WinningRank;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResultStatistics {
    private final List<Lotto> lottos;
    private final WinningNumbers winningNumbers;
    private Map<WinningRank, Integer> result;

    public ResultStatistics(List<Lotto> lottos, WinningNumbers winningNumbers) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
        calculateStatistics();
    }


    private void calculateStatistics() {
        result = new EnumMap<>(WinningRank.class);
        for (Lotto lotto : lottos) {
            WinningRank rank = new LottoResult(lotto, winningNumbers).getResult();
            if (rank == WinningRank.NONE) {
                continue;
            }
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
    }

    public int calculatePrize() {
        int prize = 0;
        for (WinningRank winningRank : result.keySet()) {
            prize += winningRank.getPrize() * result.get(winningRank);
        }
        return prize;
    }

    public double calculateRateOfReturn(){
        int prize = calculatePrize();
        int totalPayment = lottos.size() * LottoInfo.LOTTO_PRICE;
        return (double) prize / totalPayment * 100;
    }

    public Map<WinningRank, Integer> getResult() {
        return result;
    }

}
