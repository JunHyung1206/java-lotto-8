package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.*;
import lotto.util.NumbersParser;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private final static String INPUT_PAYMENT_PROMPT = "구입금액을 입력해 주세요.";
    private final static String INPUT_LOTTO_PROMPT = "당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_PROMPT = "보너스 번호를 입력해 주세요.";

    public int inputPayment() throws BaseInvalidException {
        System.out.println(INPUT_PAYMENT_PROMPT);
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new PaymentInvalidException(ErrorMessages.INVALID_INPUT_ERROR);
        }
    }

    public List<Integer> inputLotto() throws BaseInvalidException {
        System.out.println(INPUT_LOTTO_PROMPT);
        try {
            return NumbersParser.toIntegerList(Console.readLine());
        } catch (NumberFormatException e) {
            throw new LottoInvalidException(ErrorMessages.INVALID_INPUT_ERROR);
        }
    }

    public int inputBonusNumber() throws BaseInvalidException {
        System.out.println(INPUT_BONUS_PROMPT);
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new WinningNumbersInvalidException(ErrorMessages.INVALID_INPUT_ERROR);
        }
    }


}
