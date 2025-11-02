package lotto.domain;

import lotto.config.LottoInfo;
import lotto.exception.ErrorMessages;
import lotto.exception.LottoValidationException;

public class ResultStatistics {
    private final long prize;
    private final double rateOfReturn;
    private static final int PERSENT = 100;

    private ResultStatistics(long prize, double rateOfReturn) {
        this.prize = prize;
        this.rateOfReturn = rateOfReturn;
    }

    public static ResultStatistics of(WinningResult winningResult, Payment payment) {
        int expectedCounts = payment.getValue() / LottoInfo.LOTTO_PRICE; // 구입한 payment에 따른 로또 장수
        int currentCounts = winningResult.totalCounts();  // 결과에 저장되어 있는 로또 장수
        if (currentCounts != expectedCounts) {
            throw new LottoValidationException(ErrorMessages.INCONSISTENT_RESULT_ERROR);
        }
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
