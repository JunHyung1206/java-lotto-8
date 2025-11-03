package lotto.service.lottoresultevaluator;

import lotto.config.LottoInfo;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningRank;

import java.util.List;

public class BooleanArrayBasedLottoResultEvaluator implements LottoResultEvaluator {
    private int match(List<Integer> lotto, List<Integer> mainNumber) {
        boolean[] present = new boolean[LottoInfo.MAX_VALUE + 1];
        mainNumber.forEach(n -> present[n] = true);
        return (int) lotto.stream().filter(n -> present[n]).count();
    }


    @Override
    public WinningRank evaluate(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = match(lotto.getNumbers(), winningNumbers.getMainNumbers().getNumbers());
        boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonusNumber().getValue());
        return WinningRank.of(matchCount, bonusMatched);
    }
}
