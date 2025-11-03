package lotto.controller;

import lotto.domain.*;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.domain.WinningNumbers;
import lotto.exception.*;
import lotto.util.LottoMapper;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService lottoPurchaseService;
    private final LottoResultService lottoResultService;

    public LottoController(InputView inputView, OutputView outputView, LottoPurchaseService lottoPurchaseService, LottoResultService lottoResultService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoPurchaseService = lottoPurchaseService;
        this.lottoResultService = lottoResultService;
    }

    public void run() {
        Payment payment = getPayment();
        List<Lotto> purchasedLottos = lottoPurchaseService.purchase(payment);
        outputView.printPurchasedLotto(LottoMapper.toPurchasedLottoDTO(purchasedLottos));

        WinningNumbers winningNumbers = getWinningNumbers();
        WinningResult winningResult = lottoResultService.calculateResult(purchasedLottos, winningNumbers);
        ResultStatistics resultStatistics = lottoResultService.aggregate(winningResult);
        outputView.printResult(LottoMapper.toResultsDataDTO(winningResult, resultStatistics));
    }

    private WinningNumbers getWinningNumbers() {
        Lotto main = readValidLotto();
        while (true) {
            try {
                return new WinningNumbers(main, readValidBonus());
            } catch (WinningNumbersInvalidException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Lotto readValidLotto() {
        while (true) {
            try {
                return new Lotto(inputView.inputLotto());
            } catch (LottoInvalidException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private BonusNumber readValidBonus() {
        while (true) {
            try {
                return new BonusNumber(inputView.inputBonusNumber());
            } catch (WinningNumbersInvalidException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Payment getPayment() {
        while (true) {
            try {
                return new Payment(inputView.inputPayment());
            } catch (PaymentInvalidException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
