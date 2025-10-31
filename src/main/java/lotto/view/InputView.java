package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {
    public int inputPayment() throws NumberFormatException {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(Console.readLine());
    }

    public List<Integer> inputLotto() throws NumberFormatException {
        System.out.println("당첨 번호를 입력해 주세요.");
        return toIntegerList(Console.readLine());
    }

    public int inputBonusNumber() throws NumberFormatException {
        System.out.println("보너스 번호를 입력해주세요.");
        return Integer.parseInt(Console.readLine());
    }

    private List<Integer> toIntegerList(String rawNumbers) {
        return Arrays.stream(rawNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
