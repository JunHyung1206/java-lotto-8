package lotto.domain.lottoresultevaluator;

import lotto.config.WinningRank;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.provider.Arguments;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoResultEvaluatorTest {

    private WinningNumbers draw;
    private LottoResultEvaluatorImpl evaluator;

    @BeforeEach
    void setUp() {
        // 당첨: 1,2,3,4,5,6 / 보너스: 7
        draw = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));
        evaluator = new LottoResultEvaluatorImpl();
    }

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("cases")
    @DisplayName("각 경우에 대해 정확한 등수를 판단한다")
    void evaluate_returns_expected_rank(List<Integer> ticket, WinningRank expected) {
        WinningRank actual = evaluator.evaluate(new Lotto(ticket), draw);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                // 1등: 6개 일치
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), WinningRank.FIRST),
                // 2등: 5개 + 보너스
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), WinningRank.SECOND),
                // 3등: 5개
                Arguments.of(List.of(1, 2, 3, 4, 5, 8), WinningRank.THIRD),
                // 4등: 4개
                Arguments.of(List.of(1, 2, 3, 4, 8, 9), WinningRank.FOURTH),
                // 5등: 3개
                Arguments.of(List.of(1, 2, 3, 8, 9, 10), WinningRank.FIFTH),
                // 꽝: 0~2개 (여기선 2개)
                Arguments.of(List.of(1, 2, 8, 9, 10, 11), WinningRank.NONE),
                // 보너스만 일치(메인 0개): 2등 아님 → 꽝
                Arguments.of(List.of(7, 8, 9, 10, 11, 12), WinningRank.NONE),
                // 순서 뒤섞여도 동일해야 함(내부 정렬 보장)
                Arguments.of(List.of(6, 5, 4, 3, 2, 1), WinningRank.FIRST)
        );
    }
}