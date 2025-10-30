package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoInfo;
import lotto.config.LottoNumberInfo;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Payment;
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
        int salesLottoCount = payment.getValue() / 1000;

        List<Lotto> saleLottos= new ArrayList<>();

        for (int i = 0; i < salesLottoCount; i++) {
            saleLottos.add(new Lotto(Randoms.pickUniqueNumbersInRange(
                    LottoNumberInfo.MIN_VALUE.getValue(),
                    LottoNumberInfo.MAX_VALUE.getValue(),
                    LottoNumberInfo.PICK_NUMBER_COUNT.getValue()))
            );
        }

        outputView.printSalesLotto(salesLottoCount, LottoMapper.toLottoDTO(saleLottos));

        // 3. 당첨된 번호를 입력받는다.
        Lotto selectedLotto = getSelectedLotto();

        // 4. 보너스 번호를 입력받는다.
        BonusNumber bonusNumber = getBonusNumber(selectedLotto);

        // 5. 로또의 결과를 확인한다.
        LottoResult lottoResult = new LottoResult(saleLottos, selectedLotto, bonusNumber);

        // 6. 해당하는 수익금을 출력한다.
        Map<LottoInfo, Integer> result = lottoResult.getResult();
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
