package lotto.exception;

public class LottoValidationException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";
    private final ErrorMessages code;

    public LottoValidationException(ErrorMessages code) {
        super(PREFIX + code.getMessage());
        this.code = code;
    }

    @Override
    public String getMessage() {
        return PREFIX + code.getMessage();
    }
}