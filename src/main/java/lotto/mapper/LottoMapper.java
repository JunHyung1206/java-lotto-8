package lotto.mapper;

import lotto.domain.Lotto;
import lotto.domain.ResultStatistics;
import lotto.domain.WinningResult;
import lotto.dto.ResultLineDTO;
import lotto.dto.ResultStatisticsDTO;
import lotto.dto.SalesLottoDTO;
import lotto.config.WinningRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoMapper {
    private static final WinningRank[] PRINT_ORDER = {
            WinningRank.FIFTH,
            WinningRank.FOURTH,
            WinningRank.THIRD,
            WinningRank.SECOND,
            WinningRank.FIRST
    };

    public static SalesLottoDTO toSalesLottoDTO(List<Lotto> lottos) {
        List <List<Integer>> salesLottoDTO = new ArrayList<>();
        for (Lotto lotto : lottos) {
            salesLottoDTO.add(lotto.getNumbers());
        }
        return new SalesLottoDTO(salesLottoDTO);
    }

    public static ResultStatisticsDTO toResultStatisticsDTO(WinningResult winningResult, ResultStatistics resultStatistics) {
        Map<WinningRank, Integer> result = winningResult.getCounts();
        List<ResultLineDTO> resultLines = new ArrayList<>();
        for (WinningRank winningRank : PRINT_ORDER) {
            resultLines.add(new ResultLineDTO(winningRank.getLabel(), winningRank.getPrize(), result.getOrDefault(winningRank,0)));
        }
        return new ResultStatisticsDTO(resultLines, resultStatistics.getRateOfReturn());
    }
}
