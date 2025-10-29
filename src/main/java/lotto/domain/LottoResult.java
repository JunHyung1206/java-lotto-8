package lotto.domain;

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


    public Map<Integer, Integer> getResult(){
        Map<Integer, Integer> list = new HashMap<>();
        list.put(1, 0);
        list.put(2, 0);
        list.put(3, 0);
        list.put(4, 0);
        list.put(5, 0);

        for (Lotto lotto : salesLotto) {
            Set<Integer> intersection = new HashSet<>(lotto.getNumbers());
            intersection.retainAll(selectedLotto.getNumbers());
            if (intersection.size() == 6) {
                list.put(1, list.get(1) + 1);
            }
            if ((intersection.size() == 5) && lotto.getNumbers().contains(bonusNumber.getValue())) {
                list.put(2, list.get(2) + 1);
            }
            if (intersection.size() == 5 && !lotto.getNumbers().contains(bonusNumber.getValue())) {
                list.put(3, list.get(3) + 1);
            }
            if (intersection.size() == 4) {
                list.put(4, list.get(4) + 1);
            }
            if (intersection.size() == 3) {
                list.put(5, list.get(5) + 1);
            }
        }
        return list;
    }
}
