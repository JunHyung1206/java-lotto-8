package lotto.domain;

import lotto.config.WinningRank;

import java.util.*;

public class LottoMatcher {
    private int match(List<Integer> lotto, List<Integer> mainNumber) {
        Set<Integer> intersection = new HashSet<>(lotto);
        intersection.retainAll(mainNumber);
        return intersection.size();
    }

    public WinningRank getResult(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = match(lotto.getNumbers(), winningNumbers.getMainNumbers().getNumbers());
        boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonusNumber().getValue());
        return WinningRank.of(matchCount, bonusMatched);
    }
}
