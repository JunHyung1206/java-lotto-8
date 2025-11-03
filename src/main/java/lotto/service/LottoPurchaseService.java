package lotto.service;

import lotto.config.LottoInfo;
import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.service.lottogenerator.LottoGenerator;

import java.util.List;
import java.util.stream.Stream;

public class LottoPurchaseService {
    private final LottoGenerator lottoGenerator;
    public LottoPurchaseService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> purchase(Payment payment){
        return Stream.generate(lottoGenerator::generate)
                .limit(calculatePurchasableCount(payment))
                .toList();
    };

    private int calculatePurchasableCount(Payment payment) {
        return payment.getValue() / LottoInfo.LOTTO_PRICE;
    }
}
