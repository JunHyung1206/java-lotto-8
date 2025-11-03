package lotto.service.lottogenerator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoInfo;
import lotto.domain.Lotto;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(
                LottoInfo.MIN_VALUE,
                LottoInfo.MAX_VALUE,
                LottoInfo.PICK_NUMBER_COUNT)
        );
    }
}
