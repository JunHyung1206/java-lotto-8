package lotto.service.lottoresultevaluator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoInfo;
import lotto.domain.*;
import lotto.service.lottogenerator.LottoGenerator;
import lotto.service.lottogenerator.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

class LottoResultEvaluatorPerformanceTest {

    // 벤치 파라미터
    static final int NUM_TICKETS   = 10_000_000;  // 생성할 로또 장수
    static final int MEASURE_ROUNDS= 3;        // 측정 반복

    @Test
    @DisplayName("BooleanArray 기반 vs Set 기반 평가 속도 비교")
    void compareOnlyTime() {
        // 동일한 테스트 데이터 준비
        WinningNumbers winningNumbers = randomWinningNumbers();
        List<Lotto> tickets = generateTickets(NUM_TICKETS);

        LottoResultEvaluator boolEval = new BooleanArrayBasedLottoResultEvaluator();
        LottoResultEvaluator setEval  = new SetBasedLottoResultEvaluator();


        // 측정 (best-of-N)
        long boolBest = Long.MAX_VALUE, setBest = Long.MAX_VALUE;
        for (int i = 0; i < MEASURE_ROUNDS; i++) {
            boolBest = Math.min(boolBest, timeMillis(() -> processAll(tickets, winningNumbers, boolEval)));
            setBest  = Math.min(setBest,  timeMillis(() -> processAll(tickets, winningNumbers, setEval)));
        }

        System.out.printf("[PERF] BooleanArrayBased: %d ms | SetBased: %d ms (tickets=%d)%n",
                boolBest, setBest, NUM_TICKETS);
    }

    private static void processAll(List<Lotto> tickets, WinningNumbers wn, LottoResultEvaluator eval) {
        long acc = 0;
        for (Lotto t : tickets) {
            acc += eval.evaluate(t, wn).ordinal();
        }
    }

    private static long timeMillis(Runnable r) {
        long s = System.nanoTime();
        r.run();
        return (System.nanoTime() - s) / 1_000_000;
    }

    private static WinningNumbers randomWinningNumbers() {
        List<Integer> integers = Randoms.pickUniqueNumbersInRange(
                LottoInfo.MIN_VALUE,
                LottoInfo.MAX_VALUE,
                LottoInfo.PICK_NUMBER_COUNT + 1);
        return new WinningNumbers(new Lotto(integers.subList(0,6)), new BonusNumber(integers.getLast()));
    }
    private static List<Lotto> generateTickets(int n) {
        LottoGenerator generator = new RandomLottoGenerator();
        List<Lotto> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            list.add(generator.generate());
        return list;
    }
}
