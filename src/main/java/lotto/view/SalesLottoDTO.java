package lotto.view;

import java.util.List;

public class SalesLottoDTO {
    List<List<Integer>> saleLotto;

    public SalesLottoDTO(List<List<Integer>> saleLotto) {
        this.saleLotto = saleLotto;
    }

    public List<List<Integer>> getSaleLotto() {
        return saleLotto;
    }
}
