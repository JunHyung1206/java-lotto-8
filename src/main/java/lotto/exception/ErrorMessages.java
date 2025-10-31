package lotto.exception;

import lotto.config.LottoInfo;

public enum ErrorMessages {
    NOT_LOTTO_COUNT_ERROR("모든 번호는 " + LottoInfo.PICK_NUMBER_COUNT.getValue() + "개여야 합니다."),
    OUT_OF_RANGE_ERROR("로또 번호는 " + LottoInfo.MIN_VALUE.getValue() + "부터 " + LottoInfo.MAX_VALUE.getValue() + " 사이의 숫자여야 합니다."),
    DUPLICATE_ERROR("중복된 정수가 있습니다."),
    SALES_ERROR("로또는 " + LottoInfo.LOTTO_PRICE.getValue() + "원 단위로 판매합니다."),
    MIN_PAYMENT_ERROR("구매액은 1원 이상입니다."),
    INVALID_INPUT_ERROR("입력 형식이 올바르지 않습니다.");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
