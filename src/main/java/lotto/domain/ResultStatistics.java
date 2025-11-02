package lotto.domain;

public class ResultStatistics {
    private final long prize;
    private final double rateOfReturn;

    public ResultStatistics(long prize, double rateOfReturn) {
        this.prize = prize;
        this.rateOfReturn = rateOfReturn;
    }

    public long getPrize() {
        return prize;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
