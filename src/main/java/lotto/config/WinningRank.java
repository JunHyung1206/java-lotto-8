package lotto.config;


import java.util.Optional;

public enum WinningRank {


    FIRST("6개 일치", 2000000000),
    SECOND("5개 일치, 보너스 볼 일치", 30000000),
    THIRD("5개 일치", 1500000),
    FOURTH("4개 일치", 50000),
    FIFTH("3개 일치", 5000);

    private final String label;
    private final int prize;

    WinningRank(String description, int prize) {
        this.label = description;
        this.prize = prize;
    }


    public String getLabel() {
        return label;
    }

    public static Optional<WinningRank> of(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) {
            return Optional.of(FIRST);
        }
        if (matchCount == 5 && bonusMatched) {
            return Optional.of(SECOND);
        }
        if (matchCount == 5) {
            return Optional.of(THIRD);
        }
        if (matchCount == 4) {
            return Optional.of(FOURTH);
        }
        if (matchCount == 3) {
            return Optional.of(FIFTH);
        }
        return Optional.empty();
    }

    public int getPrize() {
        return prize;
    }

}
