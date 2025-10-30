package lotto.domain;

import lotto.config.LottoInfo;

import java.util.List;
import java.util.stream.Stream;

public class LottoSeller {
    private final LottoGenerator lottoGenerator;
    public LottoSeller(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> sellLottos(Payment payment) {
        return Stream.generate(lottoGenerator::generate)
                .limit(calculatePurchasableCount(payment))
                .toList();
    }

    private int calculatePurchasableCount(Payment payment) {
        return payment.getValue() / LottoInfo.LOTTO_PRICE.getValue();
    }
}
