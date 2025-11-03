package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.service.lottogenerator.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPurchaseServiceTest {
    LottoPurchaseService lottoPurchaseService;

    @Test
    @DisplayName("각 구매액수에 맞게 로또를 생성한다.")
    void successCase() {
        // given
        lottoPurchaseService = new LottoPurchaseService(new RandomLottoGenerator());
        Payment payment1 = new Payment(8000);
        Payment payment2 = new Payment(100000);

        // when
        List<Lotto> lottos1 = lottoPurchaseService.purchase(payment1);
        List<Lotto> lottos2 = lottoPurchaseService.purchase(payment2);

        // then
        assertThat(lottos1).hasSize(8);
        assertThat(lottos2).hasSize(100);
        assertThat(lottos1.getFirst().getNumbers()).hasSize(6);
        assertThat(lottos2.getFirst().getNumbers()).hasSize(6);
    }

}
