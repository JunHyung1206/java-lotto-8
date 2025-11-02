package lotto.domain;

import lotto.config.LottoInfo;

public class ResultStatistics {
    private final long prize;
    private final double rateOfReturn;
    private static final int PERSENT = 100;

    private ResultStatistics(long prize, double rateOfReturn) {
        this.prize = prize;
        this.rateOfReturn = rateOfReturn;
    }

    public static ResultStatistics of(WinningResult winningResult) {
        int purchasedPayment = winningResult.totalCounts() * LottoInfo.LOTTO_PRICE;
        long totalPrize = winningResult.calculatePrize();
        double rateOfReturn = ((double) totalPrize / purchasedPayment) * PERSENT;
        return new ResultStatistics(totalPrize, rateOfReturn);
    }

    public long getPrize() {
        return prize;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
