package lotto.controller;

import lotto.config.WinningRank;
import lotto.domain.*;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.mapper.LottoMapper;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void process() {
        Payment payment = getPayment();
        LottoSeller lottoSeller = new LottoSeller(new RandomLottoGenerator());
        List<Lotto> lottos = lottoSeller.sellLottos(payment);
        outputView.printSalesLotto(lottos.size(), LottoMapper.toLottoDTO(lottos));
        Lotto mainNumbers = getSelectedLotto();
        BonusNumber bonusNumber = getBonusNumber(mainNumbers);
        WinningNumbers winningNumbers = new WinningNumbers(mainNumbers, bonusNumber);
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);
        for (WinningRank value : WinningRank.values()) {
            result.put(value, 0);
        }
        for (Lotto lotto : lottos) {
            LottoResult lottoResult = new LottoResult(lotto, winningNumbers);
            WinningRank winningRank = lottoResult.getResult();
            result.put(winningRank, result.getOrDefault(winningRank, 0) + 1);
        }

        outputView.printResult(result, payment);
    }


    private Payment getPayment() {
        Payment payment = null;
        while (payment == null) {
            try {
                payment = new Payment(inputView.inputPayment());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return payment;
    }

    private Lotto getSelectedLotto() {
        Lotto lotto = null;
        while (lotto == null) {
            try {
                lotto = new Lotto(inputView.inputLotto());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return lotto;
    }

    private BonusNumber getBonusNumber(Lotto lotto) {
        BonusNumber bonusNumber = null;
        while (bonusNumber == null) {
            try {
                bonusNumber = new BonusNumber(inputView.inputBonusNumber(), lotto);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return bonusNumber;
    }
}
