package lotto.domain;

import lotto.config.WinningRank;

import java.util.*;

public class LottoResult {
    private final List<Lotto> salesLotto;
    private final Lotto selectedLotto;
    private final BonusNumber bonusNumber;

    public LottoResult(List<Lotto> salesLotto, Lotto selectedLotto, BonusNumber bonusNumber) {
        this.salesLotto = salesLotto;
        this.selectedLotto = selectedLotto;
        this.bonusNumber = bonusNumber;
    }


    public Map<WinningRank, Integer> getResult() {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (WinningRank value : WinningRank.values()) {
            result.put(value, 0);
        }
        for (Lotto lotto : salesLotto) {
            Set<Integer> intersection = new HashSet<>(lotto.getNumbers());
            intersection.retainAll(selectedLotto.getNumbers());
            int matchCount = intersection.size();
            boolean bonusMatched = lotto.getNumbers().contains(bonusNumber.getValue());
            WinningRank.of(matchCount, bonusMatched).ifPresent(rank -> result.put(rank, result.get(rank) + 1));
        }
        return result;
    }
}
