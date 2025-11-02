package lotto.domain.lottoresultevaluator;

import lotto.config.WinningRank;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;

import java.util.*;

public class LottoResultEvaluatorImpl implements LottoResultEvaluator {
    private int match(List<Integer> lotto, List<Integer> mainNumber) {
        Set<Integer> intersection = new HashSet<>(lotto);
        intersection.retainAll(mainNumber);
        return intersection.size();
    }

    @Override
    public WinningRank evaluate(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = match(lotto.getNumbers(), winningNumbers.getMainNumbers().getNumbers());
        boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonusNumber().getValue());
        return WinningRank.of(matchCount, bonusMatched);
    }
}
