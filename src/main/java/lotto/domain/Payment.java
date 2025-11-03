package lotto.domain;

import lotto.config.LottoInfo;
import lotto.exception.ErrorMessages;
import lotto.exception.PaymentInvalidException;

public class Payment {
    private final int value;

    public Payment(int payment) {
        validate(payment);
        this.value = payment;
    }

    private void validate(int payment) {
        if (payment < LottoInfo.LOTTO_PRICE) {
            throw new PaymentInvalidException(ErrorMessages.MIN_PAYMENT_ERROR);
        }
        if (payment % LottoInfo.LOTTO_PRICE != 0) {
            throw new PaymentInvalidException(ErrorMessages.SALES_ERROR);
        }
    }


    public int getValue() {
        return value;
    }
}
