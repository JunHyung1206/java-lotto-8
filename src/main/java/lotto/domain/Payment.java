package lotto.domain;

public class Payment {
    private final int value;

    public Payment(String paymentValue) {
        validate(paymentValue);
        this.value = toInteger(paymentValue);
    }

    private void validate(String paymentValue) {
        int payment;
        try{
            payment = toInteger(paymentValue);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요.");
        }
        if (payment <= 0) {
            throw new IllegalArgumentException("[ERROR] 구매액은 1원 이상입니다.");
        }
        if (payment % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 로또는 1000원 단위로 판매합니다.");
        }
    }

    private static int toInteger(String paymentValue) {
        return Integer.parseInt(paymentValue);
    }

    public int getValue() {
        return value;
    }
}
