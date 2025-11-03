package lotto.dto;

import java.util.List;

public record ResultsDataDTO(List<ResultLineDTO> resultLine, double rateOfReturn) {
}
