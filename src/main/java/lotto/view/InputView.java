package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {
    public int inputPayment() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            try {
                return Integer.parseInt(Console.readLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자만 입력해주세요.");
            }
        }
    }

    public List<Integer> inputLotto() {
        while (true) {
            System.out.println("당첨 번호를 입력해 주세요.");
            try {
                return toIntegerList(Console.readLine());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 입력된 형식이 올바르지 않습니다. 숫자와 숫자 사이는 ,로 구분합니다.");
            }
        }
    }

    private List<Integer> toIntegerList(String rawNumbers) {
        return Arrays.stream(rawNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
