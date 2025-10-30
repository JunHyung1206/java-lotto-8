package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoSellerTest {
    LottoSeller lottoSeller;

    @Test
    @DisplayName("각 구매액수에 맞게 로또를 생성한다.")
    void successCase(){
        // given
        lottoSeller = new LottoSeller(new RandomLottoGenerator());
        Payment payment1 = new Payment(8000);
        Payment payment2 = new Payment(100000);

        // when
        List<Lotto> lottos1 = lottoSeller.sellLottos(payment1);
        List<Lotto> lottos2 = lottoSeller.sellLottos(payment2);

        // then
        assertThat(lottos1).hasSize(8);
        assertThat(lottos2).hasSize(100);
        assertThat(lottos1.getFirst().getNumbers()).hasSize(6);
        assertThat(lottos2.getFirst().getNumbers()).hasSize(6);

    }
}