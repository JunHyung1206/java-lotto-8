
package lotto.dto;

import java.util.List;

public class ResultStatisticsDTO {
    private final List<ResultLineDTO> resultLine;
    private final double rateOfReturn;

    public ResultStatisticsDTO(List<ResultLineDTO> resultLine, double rateOfReturn) {
        this.resultLine = resultLine;
        this.rateOfReturn = rateOfReturn;
    }

    public List<ResultLineDTO> getResultLine() {
        return resultLine;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
