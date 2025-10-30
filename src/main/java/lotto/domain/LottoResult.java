package lotto.domain;

import lotto.config.LottoInfo;

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


    public Map<LottoInfo, Integer> getResult(){
        Map<LottoInfo, Integer> result = new HashMap<>();
        for (LottoInfo value : LottoInfo.values()) {
            result.put(value, 0);
        }
        for (Lotto lotto : salesLotto) {
            Set<Integer> intersection = new HashSet<>(lotto.getNumbers());
            intersection.retainAll(selectedLotto.getNumbers());
            if (intersection.size() == 6) {
                result.put(LottoInfo.FIRST, result.get(LottoInfo.FIRST) + 1);
            }
            if ((intersection.size() == 5) && lotto.getNumbers().contains(bonusNumber.getValue())) {
                result.put(LottoInfo.SECOND, result.get(LottoInfo.SECOND) + 1);
            }
            if (intersection.size() == 5 && !lotto.getNumbers().contains(bonusNumber.getValue())) {
                result.put(LottoInfo.THIRD, result.get(LottoInfo.THIRD) + 1);
            }
            if (intersection.size() == 4) {
                result.put(LottoInfo.FOURTH, result.get(LottoInfo.FOURTH) + 1);
            }
            if (intersection.size() == 3) {
                result.put(LottoInfo.FIFTH, result.get(LottoInfo.FIFTH) + 1);
            }
        }
        return result;
    }
}
