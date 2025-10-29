package lotto.domain;

public class Payment {
    private final int value;

    public Payment(int payment) {
        validate(payment);
        this.value = payment;
    }

    private void validate(int payment) {
        if (payment <= 0) {
            throw new IllegalArgumentException("[ERROR] 구매액은 1원 이상입니다.");
        }
        if (payment % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 로또는 1000원 단위로 판매합니다.");
        }
    }


    public int getValue() {
        return value;
    }
}
