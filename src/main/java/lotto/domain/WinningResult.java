package lotto.domain;

import lotto.config.WinningRank;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private final Map<WinningRank, Integer> counts;

    public WinningResult(Map<WinningRank, Integer> counts) {
        this.counts = Collections.unmodifiableMap(new EnumMap<>(counts));
    }

    public Map<WinningRank, Integer> getCounts() {
        return counts;
    }

    public int getCount(WinningRank rank) {
        return counts.getOrDefault(rank, 0);
    }
}
