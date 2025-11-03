package lotto.service.lottoresultevaluator;

import lotto.domain.WinningRank;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;

public interface LottoResultEvaluator {
    WinningRank evaluate(Lotto lotto, WinningNumbers winningNumbers);
}
