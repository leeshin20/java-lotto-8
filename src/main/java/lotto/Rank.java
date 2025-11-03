package lotto;

public enum Rank {
    FIRST(6, 2_000_000_000L, "6개 일치 (%,d원)"),
    SECOND(5, 30_000_000L, "5개 일치, 보너스 볼 일치 (%,d원)"),
    THIRD(5, 1_500_000L, "5개 일치 (%,d원)"),
    FOURTH(4, 50_000L, "4개 일치 (%,d원)"),
    FIFTH(3, 5_000L, "3개 일치 (%,d원)"),
    MISS(0, 0L, "");

    private final int matchCount;
    private final long prizeMoney;
    private final String messageFormat;

    Rank(int matchCount, long prizeMoney, String messageFormat) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.messageFormat = messageFormat;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public String getFormattedMessage(int count) {
        if (this == MISS) {
            return "";
        }
        return String.format(messageFormat, prizeMoney) + " - " + count + "개";
    }

    public static Rank valueOf(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5) {
            if (bonusMatch) {
                return SECOND;
            }
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return MISS;
    }
}
