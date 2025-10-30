package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PaymentTest {
    Payment payment;

    @ParameterizedTest
    @DisplayName("구매 금액은 양의 정수여야 한다.")
    @ValueSource(ints = {
            1000,
            2000,
            10000,
            80000
    })
    void successCaseTest(int input) {
        payment = new Payment(input);
        assertThat(payment.getValue()).isEqualTo(input);
    }


    @ParameterizedTest
    @DisplayName("구매 금액은 양의 정수여야 한다.")
    @ValueSource(ints = {
            -1000,
            -2000,
            0
    })
    void notPositiveCase(int input) {
        assertThrows(IllegalArgumentException.class, () -> new Payment(input));
    }

    @ParameterizedTest
    @DisplayName("구매 금액은 천원단위여야 한다.")
    @ValueSource(ints = {
            1234,
            1001,
            1,
            100
    })
    void cannotSellLottoCase(int input) {
        assertThrows(IllegalArgumentException.class, () -> new Payment(input));
    }
}