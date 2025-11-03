package lotto.view;

import lotto.dto.ResultLineDTO;
import lotto.dto.ResultsDataDTO;
import lotto.dto.PurchasedLottoDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        printResultHeader();
        printResultLines(resultStatisticsDTO);
        System.out.println("총 수익률은 " + round(resultStatisticsDTO.rateOfReturn()) + "%입니다.");
    }

    public String round(double value) {
        DecimalFormat percentFormat = new DecimalFormat("#,##0.0");
        percentFormat.setRoundingMode(RoundingMode.HALF_UP);
        return percentFormat.format(BigDecimal.valueOf(value));
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
