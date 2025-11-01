package lotto.domain;

import lotto.config.WinningRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    LottoResult lottoResult;
    WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
    }

    @Test
    void successTest() {
        Lotto firstTestLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        lottoResult = new LottoResult(firstTestLotto, winningNumbers);
        WinningRank result = lottoResult.getResult();

        assertThat(result).isInstanceOf(WinningRank.class);
        assertThat(result).isEqualTo(WinningRank.FIRST);

        Lotto secondTestLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        lottoResult = new LottoResult(secondTestLotto, winningNumbers);
        result = lottoResult.getResult();

        assertThat(result).isEqualTo(WinningRank.SECOND);

        Lotto thirdTestLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        lottoResult = new LottoResult(thirdTestLotto, winningNumbers);
        result = lottoResult.getResult();

        assertThat(result).isEqualTo(WinningRank.THIRD);

    }

    @Test
    void 성능테스트() {
        long start = System.nanoTime();
        long count = 100000;

        for (int i = 0; i < count; i++) {
            MatchValues.matchCount1(new Lotto(List.of(1, 2, 3, 4, 5, 8)), winningNumbers);
        }

        long elapsedNanos = System.nanoTime() - start;
        double elapsedMs = elapsedNanos / 1_000_000.0;
        System.out.printf("took %.3f ms%n", elapsedMs);

        start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            MatchValues.matchCount2(new Lotto(List.of(1, 2, 3, 4, 5, 8)), winningNumbers);
        }

        elapsedNanos = System.nanoTime() - start;
        elapsedMs = elapsedNanos / 1_000_000.0;
        System.out.printf("took %.3f ms%n", elapsedMs);

        start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            MatchValues.matchCount3(new Lotto(List.of(1, 2, 3, 4, 5, 8)), winningNumbers);
        }

        elapsedNanos = System.nanoTime() - start;
        elapsedMs = elapsedNanos / 1_000_000.0;
        System.out.printf("took %.3f ms%n", elapsedMs);

    }

    void timer(){

    }

    class MatchValues{
        static int matchCount1(Lotto lotto, WinningNumbers winningNumbers){
            Set<Integer> intersection = new HashSet<>(lotto.getNumbers());
            intersection.retainAll(winningNumbers.getMainNumbers().getNumbers());
            return intersection.size();
        }


        private static long toMask(List<Integer> nums) {
            long m = 0L;
            for (int n : nums) m |= 1L << n;   // n은 1..45 보장
            return m;
        }

        static int matchCount2(Lotto lotto, WinningNumbers winningNumbers) {
            long lottoMask = toMask(lotto.getNumbers());
            long winMask   = toMask(winningNumbers.getMainNumbers().getNumbers());

            int matchCount = Long.bitCount(lottoMask & winMask);

            boolean bonusMatched =
                    (lottoMask & (1L << winningNumbers.getBonusNumber().getValue())) != 0;

            return matchCount;
        }

        private static int countIntersectionSorted(List<Integer> a, List<Integer> b) {
            int i = 0, j = 0, cnt = 0;
            while (i < a.size() && j < b.size()) {
                int x = a.get(i), y = b.get(j);
                if (x == y) { cnt++; i++; j++; }
                else if (x < y) i++;
                else j++;
            }
            return cnt;
        }


        static int matchCount3(Lotto lotto, WinningNumbers winningNumbers) {

            int matchCount = countIntersectionSorted(lotto.getNumbers(), winningNumbers.getMainNumbers().getNumbers());

            boolean bonusMatched = false;

            return matchCount;
        }
    }
}
