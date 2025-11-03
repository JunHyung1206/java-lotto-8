package lotto.domain;

public enum WinningRank {
    FIRST("6개 일치", 2_000_000_000),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000),
    THIRD("5개 일치", 1500_000),
    FOURTH("4개 일치", 50_000),
    FIFTH("3개 일치", 5_000),
    NONE("그외", 0);

    private final String label;
    private final long prize;
    private static final WinningRank[] MATCH_COUNT_TABLE = {NONE, NONE, NONE, FIFTH, FOURTH, THIRD, FIRST};

    WinningRank(String description, int prize) {
        this.label = description;
        this.prize = prize;
    }

    public static WinningRank of(int matchCount, boolean bonusMatched) {
        if (matchCount == 5 && bonusMatched) {
            return SECOND;
        }
        return MATCH_COUNT_TABLE[matchCount];
    }

    public String getLabel() {
        return label;
    }

    public long getPrize() {
        return prize;
    }
}
