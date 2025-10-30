package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoNumberInfo;

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
                    LottoNumberInfo.MIN_VALUE.getValue(),
                    LottoNumberInfo.MAX_VALUE.getValue(),
                    LottoNumberInfo.PICK_NUMBER_COUNT.getValue()))
            );
        }
        return saleLotto;
    }

    public int salesLottoCount(){
        return payment.getValue() / 1000;
    }
}
