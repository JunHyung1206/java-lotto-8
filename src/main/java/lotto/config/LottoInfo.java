package lotto.config;

import java.text.DecimalFormat;

public enum LottoInfo {
    FIRST("6개 일치",2000000000),
    SECOND("5개 일치, 보너스 볼 일치",30000000),
    THIRD("5개 일치",1500000),
    FOURTH("4개 일치", 50000),
    FIFTH("3개 일치", 5000);

    private final String description;
    private final int money;

    LottoInfo(String description, int money) {
        this.description = description;
        this.money = money;
    }

    public String getDescription() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return description + " (" + decimalFormat.format(money) + "원)";
    }

    public int getMoney() {
        return money;
    }
}
