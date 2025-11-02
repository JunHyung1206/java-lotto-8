package lotto.service;

import lotto.config.LottoInfo;
import lotto.config.WinningRank;
import lotto.domain.*;
import lotto.domain.WinningNumbers;
import lotto.domain.lottoresultevaluator.LottoResultEvaluator;
import lotto.exception.ErrorMessages;
import lotto.exception.LottoValidationException;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {
    private final LottoResultEvaluator lottoResultEvaluator;

    public LottoResultService(LottoResultEvaluator lottoResultEvaluator) {
        this.lottoResultEvaluator = lottoResultEvaluator;
    }

    public WinningResult calculateResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (WinningRank r : WinningRank.values()) {
            result.put(r, 0);
        }
        for (Lotto lotto : lottos) {
            WinningRank rank = lottoResultEvaluator.evaluate(lotto, winningNumbers);
            result.merge(rank, 1, Integer::sum);
        }
        return new WinningResult(result);
    }

    public ResultStatistics aggregate(WinningResult winningResult, Payment payment) {
        int expectedCounts = payment.getValue() / LottoInfo.LOTTO_PRICE; // 구입한 payment에 따른 로또 장수
        int currentCounts = winningResult.totalCounts();  // 결과에 저장되어 있는 로또 장수
        if (expectedCounts != currentCounts) {
            throw new LottoValidationException(ErrorMessages.INCONSISTENT_RESULT_ERROR);
        }
        return ResultStatistics.of(winningResult, payment);
    }
}
