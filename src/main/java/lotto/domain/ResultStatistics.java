package lotto.domain;

public class ResultStatistics {
    private final long prize;
    private final double rateOfReturn;
    private static final int PERSENT = 100;

    private ResultStatistics(long prize, double rateOfReturn) {
        this.prize = prize;
        this.rateOfReturn = rateOfReturn;
    }
    public static ResultStatistics of(WinningResult winningResult, Payment payment) {
        long totalPrize = winningResult.calculatePrize();
        int paymentValue = payment.getValue();
        double rateOfReturn = ((double) totalPrize / paymentValue) * PERSENT;
        return new ResultStatistics(totalPrize, rateOfReturn);
    }

    public long getPrize() {
        return prize;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
