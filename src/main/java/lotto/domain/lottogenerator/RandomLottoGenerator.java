package lotto.domain.lottogenerator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoInfo;
import lotto.domain.Lotto;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(
                LottoInfo.MIN_VALUE.getValue(),
                LottoInfo.MAX_VALUE.getValue(),
                LottoInfo.PICK_NUMBER_COUNT.getValue())
        );
    }
}
