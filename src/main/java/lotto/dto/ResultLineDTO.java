package lotto.dto;

public class ResultLineDTO {
    private final String label;
    private final int prize;
    private final int count;

    public String getLabel() {
        return label;
    }

    public int getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public ResultLineDTO(String label, int prize, int count) {
        this.label = label;
        this.prize = prize;
        this.count = count;
    }
}
