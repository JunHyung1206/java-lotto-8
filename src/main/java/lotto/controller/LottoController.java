package lotto.controller;

import lotto.config.WinningRank;
import lotto.domain.*;
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
        // 1. 입력을 받는다. & 입력 값을 검증받는다.
        Payment payment = getPayment();

        // 2. 해당하는 개수만큼 로또를 받는다.
        LottoSeller lottoSeller = new LottoSeller(payment);
        List<Lotto> lottos = lottoSeller.saleLotto();
        outputView.printSalesLotto(lottoSeller.salesLottoCount(), LottoMapper.toLottoDTO(lottos));

        // 3. 당첨된 번호를 입력받는다.
        Lotto selectedLotto = getSelectedLotto();

        // 4. 보너스 번호를 입력받는다.
        BonusNumber bonusNumber = getBonusNumber(selectedLotto);

        // 5. 로또의 결과를 확인한다.
        LottoResult lottoResult = new LottoResult(lottos, selectedLotto, bonusNumber);

        // 6. 해당하는 수익금을 출력한다.
        Map<WinningRank, Integer> result = lottoResult.getResult();
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
