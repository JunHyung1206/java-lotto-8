package lotto.domain.lottoresultevaluator;

import lotto.config.WinningRank;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;

public interface LottoResultEvaluator {
    WinningRank evaluate(Lotto lotto, WinningNumbers winningNumbers);
}
