package lotto.config;

public enum WinningRank {
    FIRST("6개 일치", 2000000000),
    SECOND("5개 일치, 보너스 볼 일치", 30000000),
    THIRD("5개 일치", 1500000),
    FOURTH("4개 일치", 50000),
    FIFTH("3개 일치", 5000),
    NONE("그외", 0);

    private final String label;
    private final int prize;

    WinningRank(String description, int prize) {
        this.label = description;
        this.prize = prize;
    }


    public String getLabel() {
        return label;
    }

    public static WinningRank of(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && bonusMatched) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public int getPrize() {
        return prize;
    }

}
