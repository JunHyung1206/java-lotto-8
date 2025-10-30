package lotto.domain;

import lotto.config.LottoInfo;

import java.util.ArrayList;
import java.util.List;

public class LottoSeller {
    private final LottoGenerator lottoGenerator;
    public LottoSeller(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> sellLottos(Payment payment) {
        List<Lotto> lottos = new ArrayList<>();
        int count = calculatePurchasableCount(payment);
        for (int i = 0; i < count; i++) {
            lottos.add(lottoGenerator.generate());
        }
        return lottos;
    }

    private int calculatePurchasableCount(Payment payment) {
        return payment.getValue() / LottoInfo.LOTTO_PRICE.getValue();
    }
}
