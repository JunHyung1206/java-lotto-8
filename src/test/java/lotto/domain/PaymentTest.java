package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PaymentTest {
    Payment payment;

    @Test
    @DisplayName("정상적인 입력에 대해서는 동작한다")
    void successCase() {
        int input = 1000;
        payment = new Payment(input);
        assertThat(payment.getValue()).isEqualTo(1000);
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
}