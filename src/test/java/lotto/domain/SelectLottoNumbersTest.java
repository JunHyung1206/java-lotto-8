package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SelectLottoNumbersTest {
    @Test
    @DisplayName("성공 케이스 : 정수가 6개로 정상적으로 들어갔을 때 정상적으로 결과가 반환된다.")
    void basicSelectNumbersTest() {
        SelectLottoNumbers selectNumbers = new SelectLottoNumbers("1,2,3,4,5,6");
        System.out.println("selectNumbers = " + selectNumbers);
        assertThat(selectNumbers.getNumbers().size()).isEqualTo(6);
    }

    @ParameterizedTest
    @DisplayName("실패 케이스 : 정수가 아닌 다른 값이 들어갔을 때 생성에 실패한다.")
    @ValueSource(strings = {
            "1,2,3,4,5,a",
            "lotto,hello2,3,4,5,a",
            "lotto,31242,3,4,5,a",
            "1@32!@#"
    })
    void notIntegerNumbersTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> new SelectLottoNumbers(input));
    }

    @ParameterizedTest
    @DisplayName("성공 케이스 : , 사이에 공백은 허용한다.")
    @ValueSource(strings = {
            "1,2,3,4,5,   6",
            "1,2,3,4, 5,   6",
            "1, 2, 3, 4, 5, 6",
            "1 ,2 ,3 ,4 ,5 ,6",
            "1 , 2 , 3 , 4 , 5 , 6",
    })
    void splitAndTrimTest(String input) {
        assertThat(new SelectLottoNumbers(input).getNumbers().size()).isEqualTo(6);
    }


    @ParameterizedTest
    @DisplayName("실패 케이스 : 리스트의 원소가 6개가 아니라면 실패한다.")
    @ValueSource(strings = {
            "1",
            "1,2,3,4,5",
            "1,2,3,4,5,6,7,8",
            "1,2,3,4,5,6,7,8,9,10"
    })
    void lengthTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> new SelectLottoNumbers(input));
    }

    @ParameterizedTest
    @DisplayName("실패 케이스 : 배열의 원소가 1에서 45 사이로 이루어져 있지 않으면 실패한다.")
    @ValueSource(strings = {
            "1,2,3,4,5,120",
            "1,2,3,4,5,50",
            "0,2,3,4,5,50",
            "-13,2,3,4,5,50",
            "-15,2123,3,4,5,50",
    })
    void notRangeTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> new SelectLottoNumbers(input));
    }

    @ParameterizedTest
    @DisplayName("실패 케이스 : 배열의 원소가 중복되어 있다면 실패한다.")
    @ValueSource(strings = {
            "1,2,3,4,5,120",
            "1,2,3,4,5,50",
            "0,2,3,4,5,50",
            "-13,2,3,4,5,50",
            "-15,2123,3,4,5,50",
    })
    void duplicateTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> new SelectLottoNumbers(input));
    }


}