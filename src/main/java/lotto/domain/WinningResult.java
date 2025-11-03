package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private final Map<WinningRank, Integer> counts;

    public WinningResult(Map<WinningRank, Integer> counts) {
        this.counts = Collections.unmodifiableMap(new EnumMap<>(counts));
    }

    public int getCount(WinningRank rank) {
        return counts.getOrDefault(rank, 0);
    }

    public long calculatePrize() {
        long prize = 0L;
        for (WinningRank winningRank : WinningRank.values()) {
            prize += winningRank.getPrize() * getCount(winningRank);
        }
        return prize;
    }

    public int totalCounts() {
        return counts.values().stream().mapToInt(Integer::intValue).sum();
    }
}
