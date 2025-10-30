package lotto.mapper;

import lotto.domain.Lotto;
import lotto.view.SalesLottoDTO;

import java.util.ArrayList;
import java.util.List;

public class LottoMapper {

    public static SalesLottoDTO toLottoDTO(List<Lotto> lottos) {
        List <List<Integer>> salesLottoDTO = new ArrayList<>();
        for (Lotto lotto : lottos) {
            salesLottoDTO.add(lotto.getNumbers());
        }
        return new SalesLottoDTO(salesLottoDTO);
    }
}
