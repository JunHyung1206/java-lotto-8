package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoInfo;

import java.util.ArrayList;
import java.util.List;

public class LottoSeller {
    private final Payment payment;

    public LottoSeller(Payment payment) {
        this.payment = payment;
    }

    public List<Lotto> saleLotto(){
        int salesLottoCount = salesLottoCount();

        List<Lotto> saleLotto = new ArrayList<>();

        for (int i = 0; i < salesLottoCount; i++) {
            saleLotto.add(new Lotto(Randoms.pickUniqueNumbersInRange(
                    LottoInfo.MIN_VALUE.getValue(),
                    LottoInfo.MAX_VALUE.getValue(),
                    LottoInfo.PICK_NUMBER_COUNT.getValue()))
            );
        }
        return saleLotto;
    }

    public int salesLottoCount(){
        return payment.getValue() / LottoInfo.LOTTO_PRICE.getValue();
    }
}
