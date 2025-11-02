package lotto.dto;

public class ResultLineDTO {
    private final String label;
    private final long prize;
    private final int count;

    public String getLabel() {
        return label;
    }

    public long getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public ResultLineDTO(String label, long prize, int count) {
        this.label = label;
        this.prize = prize;
        this.count = count;
    }
}
