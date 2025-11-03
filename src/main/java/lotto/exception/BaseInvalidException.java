package lotto.exception;

public abstract class BaseInvalidException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR]";

    public BaseInvalidException(ErrorMessages code) {
        super("%s %s".formatted(PREFIX, code.getMessage()));
    }
}
