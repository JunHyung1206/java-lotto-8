package lotto.controller;

import lotto.domain.*;
import lotto.mapper.LottoMapper;
import lotto.service.LottoPurchaseService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService lottoPurchaseService;

    public LottoController(InputView inputView, OutputView outputView, LottoPurchaseService lottoPurchaseService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoPurchaseService = lottoPurchaseService;
    }
    
    public void run() {
        Payment payment = getPayment();
        List<Lotto> lottos = lottoPurchaseService.purchase(payment);
        outputView.printSalesLotto(LottoMapper.toSalesLottoDTO(lottos));
        Lotto mainNumbers = getSelectedLotto();
        BonusNumber bonusNumber = getBonusNumber(mainNumbers);
        WinningNumbers winningNumbers = new WinningNumbers(mainNumbers, bonusNumber);
        ResultStatistics resultStatistics = new ResultStatistics(lottos, winningNumbers);
        outputView.printResult(LottoMapper.toResultStatisticsDTO(resultStatistics));
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
                bonusNumber = new BonusNumber(inputView.inputBonusNumber(), lotto);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
        return bonusNumber;
    }
}
