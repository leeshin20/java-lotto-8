package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("1등인 경우")
    @Test
    void testRankFirst() {
        Rank rank = Rank.valueOf(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrizeMoney()).isEqualTo(2_000_000_000L);
    }

    @DisplayName("2등인 경우")
    @Test
    void testRankSecond() {
        Rank rank = Rank.valueOf(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrizeMoney()).isEqualTo(30_000_000L);
    }

    @DisplayName("꽝인 경우")
    @Test
    void testRankmiss() {
        String message = Rank.MISS.getFormattedMessage(100);
        assertThat(message).isEmpty();
    }
}
