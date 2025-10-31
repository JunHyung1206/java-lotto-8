package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.ResultStatistics;
import lotto.domain.WinningNumbers;

import java.util.List;

public class LottoResultService {
    public ResultStatistics aggregate(List<Lotto> lottos, WinningNumbers winningNumbers) {
        return new ResultStatistics(lottos, winningNumbers);
    }
}
