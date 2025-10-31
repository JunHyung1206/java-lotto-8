
package lotto.dto;

import lotto.config.WinningRank;

import java.util.Map;

public class ResultStatisticsDTO {
    private final Map<WinningRank, Integer> result;
    private final int prize;
    private final double rateOfReturn;

    public ResultStatisticsDTO(Map<WinningRank, Integer> result, int prize, double rateOfReturn) {
        this.result = result;
        this.prize = prize;
        this.rateOfReturn = rateOfReturn;
    }

    public Map<WinningRank, Integer> getResult() {
        return result;
    }

    public int getPrize() {
        return prize;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
