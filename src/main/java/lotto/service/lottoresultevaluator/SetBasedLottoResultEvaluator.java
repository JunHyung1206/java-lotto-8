package lotto.service.lottoresultevaluator;

import lotto.domain.WinningRank;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetBasedLottoResultEvaluator implements LottoResultEvaluator {
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
