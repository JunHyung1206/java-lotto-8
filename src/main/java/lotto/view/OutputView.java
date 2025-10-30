package lotto.view;

import lotto.config.LottoInfo;
import lotto.domain.Payment;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final LottoInfo[] PRINT_ORDER = {
            LottoInfo.FIFTH,   // 3개 일치
            LottoInfo.FOURTH,  // 4개 일치
            LottoInfo.THIRD,   // 5개 일치
            LottoInfo.SECOND,  // 5개 + 보너스
            LottoInfo.FIRST    // 6개 일치
    };


    public void printSalesLotto(int salesLottoCount, SalesLottoDTO lottoDTO) {
        System.out.println(salesLottoCount + "개를 구매했습니다.");
        List<List<Integer>> saleLotto = lottoDTO.getSaleLotto();
        for (List<Integer> lotto : saleLotto) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public void printResult(Map<LottoInfo, Integer> result, Payment payment) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        for (LottoInfo lottoInfo : LottoInfo.values()) {
            System.out.println(lottoInfo.getDescription() + " - " + result.get(lottoInfo)+"개");
        }

        // 총 상금(Long) 누적
        long totalPrize = 0L;
        for (LottoInfo lottoInfo : PRINT_ORDER) {
            int count = result.getOrDefault(lottoInfo, 0);
            totalPrize += (long) count * lottoInfo.getMoney();
        }

        // 수익률 계산: 소수점 둘째 자리에서 반올림 → 소수 첫째 자리까지, 천단위 구분
        double yieldPercent = (double) totalPrize / payment.getValue() * 100.0;
        DecimalFormat percentFormat = new DecimalFormat("#,##0.0");

        System.out.println("총 수익률은 " + percentFormat.format(yieldPercent) + "%입니다.");
    }
}
