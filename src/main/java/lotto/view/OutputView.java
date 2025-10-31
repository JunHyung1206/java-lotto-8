package lotto.view;

import lotto.dto.ResultLineDTO;
import lotto.dto.ResultStatisticsDTO;
import lotto.dto.SalesLottoDTO;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    public void printSalesLotto(SalesLottoDTO lottoDTO) {
        System.out.println(lottoDTO.getSaleLotto().size() + "개를 구매했습니다.");
        List<List<Integer>> saleLotto = lottoDTO.getSaleLotto();
        for (List<Integer> lotto : saleLotto) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public void printResult(ResultStatisticsDTO resultStatisticsDTO) {
        printResultHeader();
        DecimalFormat moneyFormat = new DecimalFormat("###,###");
        List<ResultLineDTO> resultLines = resultStatisticsDTO.getResultLine();
        for (ResultLineDTO resultLine : resultLines) {
            System.out.printf("%s (%s원) - %d개\n",resultLine.getLabel(), moneyFormat.format(resultLine.getPrize()),resultLine.getCount());
        }

        DecimalFormat percentFormat = new DecimalFormat("#,##0.0");
        System.out.println("총 수익률은 " + percentFormat.format(resultStatisticsDTO.getRateOfReturn()) + "%입니다.");
    }

    private void printResultHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
