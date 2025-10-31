package lotto.mapper;

import lotto.domain.Lotto;
import lotto.domain.ResultStatistics;
import lotto.dto.ResultStatisticsDTO;
import lotto.dto.SalesLottoDTO;

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

    public static ResultStatisticsDTO toResultStatisticsDTO(ResultStatistics resultStatistics) {

        return new ResultStatisticsDTO(resultStatistics.getResult(), resultStatistics.calculatePrize(), resultStatistics.calculateRateOfReturn());
    }
}
