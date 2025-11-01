package lotto.domain;

public class ResultStatistics {
    private final int prize;
    private final double rateOfReturn;

    public ResultStatistics(int prize, double rateOfReturn) {
        this.prize = prize;
        this.rateOfReturn = rateOfReturn;
    }

    public int getPrize() {
        return prize;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
