package lotto.domain;

import lotto.config.WinningRank;

import java.util.*;

public class LottoResult {
    private final Lotto lotto;
    private final WinningNumbers winningNumbers;

    public LottoResult(Lotto lotto, WinningNumbers winningNumbers) {
        this.lotto = lotto;
        this.winningNumbers = winningNumbers;
    }


    public WinningRank getResult() {
        Set<Integer> intersection = new HashSet<>(lotto.getNumbers());
        intersection.retainAll(winningNumbers.getMainNumbers().getNumbers());
        int matchCount = intersection.size();
        boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonusNumber().getValue());

        return WinningRank.of(matchCount, bonusMatched);
    }
}
