package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoControllerTest {

    private LottoController controller;

    @BeforeEach
    void setUp() {
        controller = new LottoController();
    }

    @DisplayName("1등에 당첨된 경우")
    @Test
    void winFirst() {
        controller = new TestLottoController(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        ));

        Map<Rank, Integer> statistics = controller.calculateStatistics(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );

        assertThat(statistics.get(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.get(Rank.SECOND)).isEqualTo(0);
        assertThat(statistics.get(Rank.THIRD)).isEqualTo(0);
        assertThat(statistics.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(statistics.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(statistics.get(Rank.MISS)).isEqualTo(0);
    }

    @DisplayName("2등에 당첨된 경우")
    @Test
    void winSecond() {
        controller = new TestLottoController(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 7))
        ));

        Map<Rank, Integer> statistics = controller.calculateStatistics(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );

        assertThat(statistics.get(Rank.FIRST)).isEqualTo(0);
        assertThat(statistics.get(Rank.SECOND)).isEqualTo(1);
    }

    @DisplayName("당첨되지 않은 경우")
    @Test
    void miss() {
        controller = new TestLottoController(List.of(
                new Lotto(List.of(1, 2, 10, 11, 12, 13))
        ));

        Map<Rank, Integer> statistics = controller.calculateStatistics(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );

        assertThat(statistics.get(Rank.MISS)).isEqualTo(1);
    }

    @DisplayName("여러 등수에 당첨된 경우")
    @Test
    void winMultiple() {
        controller = new TestLottoController(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        ));

        Map<Rank, Integer> statistics = controller.calculateStatistics(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );

        assertThat(statistics.get(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.get(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.get(Rank.THIRD)).isEqualTo(0);
        assertThat(statistics.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(statistics.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(statistics.get(Rank.MISS)).isEqualTo(1);
    }

    private static class TestLottoController extends LottoController {
        public TestLottoController(List<Lotto> lottos) {
            super();
            setLottos(lottos);
        }

        private void setLottos(List<Lotto> lottos) {
            try {
                java.lang.reflect.Field field = LottoController.class.getDeclaredField("lottos");
                field.setAccessible(true);
                field.set(this, lottos);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
