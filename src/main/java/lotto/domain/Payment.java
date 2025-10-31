package lotto.domain;

import lotto.exception.ErrorMessages;
import lotto.exception.LottoValidationException;

public class Payment {
    private final int value;

    public Payment(int payment) {
        validate(payment);
        this.value = payment;
    }

    private void validate(int payment) {
        if (payment <= 0) {
            throw new LottoValidationException(ErrorMessages.MIN_PAYMENT_ERROR);
        }
        if (payment % 1000 != 0) {
            throw new LottoValidationException(ErrorMessages.SALES_ERROR);
        }
    }


    public int getValue() {
        return value;
    }
}
