package lotto;

import java.util.List;

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
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public static void promptBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public static void displayWinnerStatistics(List<Lotto> lottos) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - 0개");
        System.out.println("4개 일치 (50,000원) - 0개");
        System.out.println("5개 일치 (1,500,000원) - 0개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - 0개");
        System.out.println("6개 일치 (2,000,000,000원) - 0개");
        System.out.println("총 수익률은 00.0%입니다.");
    }
}

