package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorMessages;
import lotto.exception.LottoValidationException;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private final static String INPUT_PAYMENT_PROMPT = "구입금액을 입력해 주세요.";
    private final static String INPUT_LOTTO_PROMPT = "당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_PROMPT = "보너스 번호를 입력해 주세요.";

    public int inputPayment() throws LottoValidationException {
        System.out.println(INPUT_PAYMENT_PROMPT);
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new LottoValidationException(ErrorMessages.INVALID_INPUT_ERROR);
        }
    }

    public List<Integer> inputLotto() throws LottoValidationException {
        System.out.println(INPUT_LOTTO_PROMPT);
        try {
            return toIntegerList(Console.readLine());
        } catch (NumberFormatException e) {
            throw new LottoValidationException(ErrorMessages.INVALID_INPUT_ERROR);
        }
    }

    public int inputBonusNumber() throws LottoValidationException {
        System.out.println(INPUT_BONUS_PROMPT);
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new LottoValidationException(ErrorMessages.INVALID_INPUT_ERROR);
        }
    }

    private List<Integer> toIntegerList(String rawNumbers) {
        return Arrays.stream(rawNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
