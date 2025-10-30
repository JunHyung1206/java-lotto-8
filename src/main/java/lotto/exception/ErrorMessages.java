package lotto.exception;

import lotto.config.LottoNumberInfo;

public enum ErrorMessages {
    NOT_LOTTO_COUNT_ERROR("[ERROR] 모든 번호는 " + LottoNumberInfo.PICK_NUMBER_COUNT.getValue() + "개여야 합니다."),
    OUT_OF_RANGE_ERROR("[ERROR] 모든 번호는 " + LottoNumberInfo.MIN_VALUE.getValue() + " ~ " + LottoNumberInfo.MAX_VALUE.getValue() + " 사이의 값으로 이루어져야 합니다."),
    DUPLICATE_ERROR("[ERROR] 중복된 정수가 있습니다."),
    SALES_ERROR("[ERROR] 로또는 1000원 단위로 판매합니다.");



    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
