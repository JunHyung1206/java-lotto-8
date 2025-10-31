package lotto.view;

import lotto.config.WinningRank;
import lotto.domain.Payment;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final WinningRank[] PRINT_ORDER = {
            WinningRank.FIFTH,   // 3개 일치
            WinningRank.FOURTH,  // 4개 일치
            WinningRank.THIRD,   // 5개 일치
            WinningRank.SECOND,  // 5개 + 보너스
            WinningRank.FIRST    // 6개 일치
    };


    public void printSalesLotto(int salesLottoCount, SalesLottoDTO lottoDTO) {
        System.out.println(salesLottoCount + "개를 구매했습니다.");
        List<List<Integer>> saleLotto = lottoDTO.getSaleLotto();
        for (List<Integer> lotto : saleLotto) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public void printResult(Map<WinningRank, Integer> result, double rateOfReturn) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        DecimalFormat moneyFormat = new DecimalFormat("###,###");
        for (WinningRank rank : PRINT_ORDER) {
            long prize = rank.getPrize();
            int count = result.getOrDefault(rank, 0);
            System.out.println(rank.getLabel() + " (" + moneyFormat.format(prize) + "원) - " + count + "개");
        }

        DecimalFormat percentFormat = new DecimalFormat("#,##0.0");
        System.out.println("총 수익률은 " + percentFormat.format(rateOfReturn) + "%입니다.");
    }
}
