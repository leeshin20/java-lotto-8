package lotto;

import java.util.List;
import java.util.Map;

public class Output {
    public static void promptCost() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void displayLottos(List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");

        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void promptWinningNumber() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public static void promptBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }

    public static void displayWinnerStatistics(Map<Rank, Integer> statistics) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        List<Rank> displayOrder = List.of(
                Rank.FIFTH,
                Rank.FOURTH,
                Rank.THIRD,
                Rank.SECOND,
                Rank.FIRST
        );
        for (Rank rank : displayOrder) {
            int count = statistics.get(rank);
            System.out.println(rank.getFormattedMessage(count));
        }
    }

    public static void displayProfitRate(Long totalPrize, int purchaseAmount) {
        double rate = ((double) totalPrize / purchaseAmount) * 100.0;
        System.out.printf("총 수익률은 %.1f%%입니다.\n", rate);
    }
}

