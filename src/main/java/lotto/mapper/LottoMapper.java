package lotto.mapper;

import lotto.domain.Lotto;
import lotto.domain.ResultStatistics;
import lotto.domain.WinningResult;
import lotto.dto.ResultLineDTO;
import lotto.dto.ResultsDataDTO;
import lotto.dto.PurchasedLottoDTO;
import lotto.domain.WinningRank;

import java.util.ArrayList;
import java.util.List;

public class LottoMapper {
    private static final WinningRank[] PRINT_ORDER = {
            WinningRank.FIFTH,
            WinningRank.FOURTH,
            WinningRank.THIRD,
            WinningRank.SECOND,
            WinningRank.FIRST
    };

    public static PurchasedLottoDTO toPurchasedLottoDTO(List<Lotto> lottos) {
        List <List<Integer>> salesLottoDTO = new ArrayList<>();
        for (Lotto lotto : lottos) {
            salesLottoDTO.add(lotto.getNumbers());
        }
        return new PurchasedLottoDTO(salesLottoDTO);
    }

    public static ResultsDataDTO toResultsDataDTO(WinningResult winningResult, ResultStatistics resultStatistics) {
        List<ResultLineDTO> resultLines = new ArrayList<>();
        for (WinningRank winningRank : PRINT_ORDER) {
            resultLines.add(new ResultLineDTO(winningRank.getLabel(), winningRank.getPrize(), winningResult.getCount(winningRank)));
        }
        return new ResultsDataDTO(resultLines, resultStatistics.getRateOfReturn());
    }
}
