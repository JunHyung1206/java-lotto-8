package lotto.domain;


import lotto.config.WinningRank;
import java.util.Map;

public class ResultStatistics {
    private final int prize;
    private final double rateOfReturn;
    private final Map<WinningRank, Integer> result;

    public ResultStatistics(Map<WinningRank, Integer> result, int prize, double rateOfReturn) {
        this.result = result;
        this.prize = prize;
        this.rateOfReturn = rateOfReturn;
    }

    public int getPrize() {
        return prize;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }

    public Map<WinningRank, Integer> getResult() {
        return result;
    }
}
