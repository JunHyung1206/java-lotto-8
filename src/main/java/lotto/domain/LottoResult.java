package lotto.domain;

import lotto.config.WinningRank;

import java.util.*;

public class LottoResult {
    private final List<Lotto> salesLotto;
    private final WinningNumbers winningNumbers;

    public LottoResult(List<Lotto> salesLotto, WinningNumbers winningNumbers) {
        this.salesLotto = salesLotto;
        this.winningNumbers = winningNumbers;
    }


    public Map<WinningRank, Integer> getResult() {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (WinningRank value : WinningRank.values()) {
            result.put(value, 0);
        }
        for (Lotto lotto : salesLotto) {
            Set<Integer> intersection = new HashSet<>(lotto.getNumbers());
            intersection.retainAll(winningNumbers.getMainNumbers().getNumbers());
            int matchCount = intersection.size();
            boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonusNumber().getValue());
            WinningRank.of(matchCount, bonusMatched).ifPresent(rank -> result.put(rank, result.get(rank) + 1));
        }
        return result;
    }
}
