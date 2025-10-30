package lotto.view;

import lotto.config.LottoInfo;
import lotto.domain.Payment;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printSalesLotto(int salesLottoCount, SalesLottoDTO lottoDTO) {
        System.out.println(salesLottoCount + "개를 구매했습니다.");
        List<List<Integer>> saleLotto = lottoDTO.getSaleLotto();
        for (List<Integer> lotto : saleLotto) {
            System.out.println(lotto);
        }
    }

    public void printResult(Map<LottoInfo, Integer> result, Payment payment) {
        for (LottoInfo lottoInfo : LottoInfo.values()) {
            System.out.println(lottoInfo.getDescription() + " - " + result.get(lottoInfo)+"개");
        }
        int sum = 0;
        for (LottoInfo lottoInfo : result.keySet()) {
            sum += result.get(lottoInfo) * lottoInfo.getMoney();
        }

        Double d = (double)sum / payment.getValue() * 100;
        System.out.println("총 수익률은 "+ d+"%입니다.");
    }
}
