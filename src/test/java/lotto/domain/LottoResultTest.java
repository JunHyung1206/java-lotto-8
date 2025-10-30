package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {

    List<Lotto> lottos;
    Lotto lotto;
    BonusNumber bonusNumber;
    @BeforeEach
    void setUp() {
        lottos = new ArrayList<>();
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        bonusNumber = new BonusNumber(7, lotto);

        List<List<Integer>> sample = List.of(List.of(8, 21, 30, 32, 38, 41),
                List.of(4, 7, 10, 17, 19, 41),
                List.of(1, 2, 4, 23, 33, 36),
                List.of(8, 21, 25, 26, 38, 42),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 8),
                List.of(7, 31, 34, 36, 42, 43),
                List.of(18, 20, 21, 26, 34, 38),
                List.of(6, 15, 16, 24, 32, 35),
                List.of(4, 6, 8, 17, 25, 45),
                List.of(1, 2, 3, 4, 5, 6));

        for (List<Integer> integers : sample) {
            lottos.add(new Lotto(integers));
        }
    }

    @Test
    void successTest(){
        LottoResult lottoResult = new LottoResult(lottos, lotto, bonusNumber);
        // Map<Integer, Integer> result = lottoResult.getResult();
        // System.out.println("result = " + result);
    }


}