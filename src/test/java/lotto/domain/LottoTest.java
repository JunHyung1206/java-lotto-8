package lotto.domain;

import lotto.exception.LottoValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {
    @Test
    @DisplayName("성공 케이스 : 정수가 6개로 정상적으로 들어갔을 때 정상적으로 결과가 반환된다.")
    void basicSelectNumbersTest() {
        Lotto selectNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        System.out.println("selectNumbers = " + selectNumbers);
        assertThat(selectNumbers.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("리스트의 원소가 6개가 아니라면 실패한다.")
    void lengthTest() {
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of()));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7, 8)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }

    @Test
    @DisplayName("배열의 원소가 1에서 45 사이로 이루어져 있지 않으면 실패한다.")
    void notRangeTest() {
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 120)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 50)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(0, 2, 3, 4, 5, 50)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(-13, 2, 3, 4, 5, 50)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(-15, 2123, 3, 4, 5, 50)));
    }

    @Test
    @DisplayName("배열의 원소가 중복되어 있다면 실패한다.")
    void duplicateTest() {
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 5)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 1)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(3, 2, 3, 4, 5, 43)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(13, 2, 13, 4, 5, 1)));
        assertThrows(LottoValidationException.class, () -> new Lotto(List.of(15, 15, 15, 15, 15, 15)));
    }

    @Test
    @DisplayName("로또는 오름차순으로 정렬된 값을 보유한다.")
    void sortedTest() {
        Lotto lotto = new Lotto(List.of(6, 2, 3, 4, 5, 1));
        assertThat(lotto.getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또의 내부값은 null이 되어서는 안된다.")
    void notNullTest() {
        assertThrows(LottoValidationException.class, () -> new Lotto(null));
    }

    @Test
    @DisplayName("Lotto의 반환 리스트는 불변이어야 한다")
    void lottoGetNumbers_isUnmodifiable() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));
        List<Integer> view = lotto.getNumbers();
        assertThrows(UnsupportedOperationException.class, () -> view.add(99));
        assertThat(view).isEqualTo(List.of(1,2,3,4,5,6));
    }
}
