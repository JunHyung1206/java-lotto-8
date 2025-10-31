package lotto.dto;

import java.util.List;

public class SalesLottoDTO {
    private final List<List<Integer>> saleLotto;

    public SalesLottoDTO(List<List<Integer>> saleLotto) {
        this.saleLotto = saleLotto;
    }

    public List<List<Integer>> getSaleLotto() {
        return saleLotto;
    }
}
