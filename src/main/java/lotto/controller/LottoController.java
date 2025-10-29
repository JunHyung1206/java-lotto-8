package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoNumberRange;
import lotto.config.LottoPickNumberCount;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Payment;
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
        // 1. 입력을 받는다.
        // 2. 입력 값을 검증받는다.
        Payment payment = getPayment();
        System.out.println("payment = " + payment.getValue());

        // 3. 해당하는 개수만큼 로또를 받는다.
        int salesLottoCount = payment.getValue() / 1000;
        System.out.println(salesLottoCount + "개를 구매했습니다.");
        List<Lotto> saleLottos= new ArrayList<>();

        for (int i = 0; i < salesLottoCount; i++) {
            saleLottos.add(new Lotto(Randoms.pickUniqueNumbersInRange(
                    LottoNumberRange.MIN_VALUE.getValue(),
                    LottoNumberRange.MAX_VALUE.getValue(),
                    LottoPickNumberCount.PICK_NUMBER_COUNT.getValue()))
            );
        }

        for (Lotto saleLotto : saleLottos) {
            System.out.println("saleLotto = " + saleLotto);
        }
        // 4. 당첨된 번호를 입력받는다.
        Lotto selectedLotto = getSelectedLotto();
        System.out.println("selectedLotto = " + selectedLotto);

        // 5. 보너스 번호를 입력받는다.
        BonusNumber bonusNumber = getBonusNumber(selectedLotto);
        System.out.println("bonusNumber = " + bonusNumber);

        // 6. 로또의 결과를 확인한다.
        LottoResult lottoResult = new LottoResult(saleLottos, selectedLotto, bonusNumber);


        // 7. 해당하는 수익금을 출력한다.
        Map<Integer, Integer> result = lottoResult.getResult();
        System.out.println("3개 일치 (5,000원) - " + result.get(5)+"개");
        System.out.println("4개 일치 (5,0000원) - "+ result.get(4)+"개");
        System.out.println("5개 일치 (1,500,000원) - "+ result.get(3)+"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+ result.get(2)+"개");
        System.out.println("6개 일치 (2,000,000,000원) - "+ result.get(1)+"개");


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
