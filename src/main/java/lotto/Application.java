package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoMatcher;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
                new InputView(),
                new OutputView(),
                new LottoPurchaseService(new RandomLottoGenerator()),
                new LottoResultService(new LottoMatcher()));
        lottoController.run();
    }
}
