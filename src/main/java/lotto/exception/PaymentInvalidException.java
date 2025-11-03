package lotto.exception;

public class PaymentInvalidException extends BaseInvalidException {
    public PaymentInvalidException(ErrorMessages code) {
        super(code);
    }
}
