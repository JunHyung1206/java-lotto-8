package lotto.domain;

public enum WinningRank {
    FIRST("6개 일치", 2_000_000_000L),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000L),
    THIRD("5개 일치", 1_500_000L),
    FOURTH("4개 일치", 50_000L),
    FIFTH("3개 일치", 5_000L),
    NONE("그외", 0L);

    private final String label;
    private final long prize;
    private static final WinningRank[] MATCH_COUNT_TABLE = {NONE, NONE, NONE, FIFTH, FOURTH, THIRD, FIRST};

    WinningRank(String description, long prize) {
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
