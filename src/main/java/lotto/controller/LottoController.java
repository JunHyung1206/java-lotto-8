package lotto.controller;

import lotto.domain.*;
import lotto.mapper.LottoMapper;
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
        outputView.printSalesLotto(LottoMapper.toSalesLottoDTO(purchasedLottos));
        WinningNumbers winningNumbers = getWinningNumbers();
        WinningResult winningResult = lottoResultService.calculateResult(purchasedLottos, winningNumbers);
        ResultStatistics resultStatistics = lottoResultService.aggregate(winningResult);
        outputView.printResult(LottoMapper.toResultStatisticsDTO(winningResult, resultStatistics));
    }

    private WinningNumbers getWinningNumbers() {
        Lotto mainNumbers = getSelectedLotto();
        BonusNumber bonusNumber = getBonusNumber(mainNumbers);

        WinningNumbers winningNumbers = null;
        while (winningNumbers == null) {
            try {
                winningNumbers = new WinningNumbers(mainNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                bonusNumber = getBonusNumber(mainNumbers);
            }
        }
        return winningNumbers;
    }

    private Payment getPayment() {
        Payment payment = null;
        while (payment == null) {
            try {
                payment = new Payment(inputView.inputPayment());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
        return payment;
    }

    private Lotto getSelectedLotto() {
        Lotto lotto = null;
        while (lotto == null) {
            try {
                lotto = new Lotto(inputView.inputLotto());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
        return lotto;
    }

    private BonusNumber getBonusNumber(Lotto lotto) {
        BonusNumber bonusNumber = null;
        while (bonusNumber == null) {
            try {
                bonusNumber = new BonusNumber(inputView.inputBonusNumber());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
        return bonusNumber;
    }
}
