package lotto.view;

import lotto.dto.ResultLineDTO;
import lotto.dto.ResultsDataDTO;
import lotto.dto.PurchasedLottoDTO;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    public void printPurchasedLotto(PurchasedLottoDTO lottoDTO) {
        System.out.println(lottoDTO.purchasedLotto().size() + "개를 구매했습니다.");
        List<List<Integer>> saleLotto = lottoDTO.purchasedLotto();
        for (List<Integer> lotto : saleLotto) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public void printResult(ResultsDataDTO resultStatisticsDTO) {
        DecimalFormat percentFormat = new DecimalFormat("#,##0.0");
        printResultHeader();
        printResultLines(resultStatisticsDTO);
        System.out.println("총 수익률은 " + percentFormat.format(resultStatisticsDTO.rateOfReturn()) + "%입니다.");
    }

    private void printResultHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    private void printResultLines(ResultsDataDTO resultStatisticsDTO) {
        DecimalFormat moneyFormat = new DecimalFormat("###,###");
        System.out.println();
        List<ResultLineDTO> resultLines = resultStatisticsDTO.resultLine();
        for (ResultLineDTO resultLine : resultLines) {
            System.out.printf("%s (%s원) - %d개%n",resultLine.label(), moneyFormat.format(resultLine.prize()),resultLine.count());
        }
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
