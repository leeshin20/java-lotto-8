package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class LottoController {
    private Integer purchaseAmount = 0;
    private List<Integer> winningNumbers = new ArrayList<>();
    private Integer bonusNumber = 0;
    private List<Lotto> lottos = new ArrayList<>();

    public void run() {
        purchaseLotto();
        drawLottoWinner();
    }

    public void purchaseLotto() {
        Output.promptCost();
        this.purchaseAmount = Input.inputPurchaseAmount();
        issueLotto(this.purchaseAmount);
        Output.displayLottos(lottos);;
    }

    public void drawLottoWinner() {
        Output.promptWinningNumber();
        this.winningNumbers = Input.inputWinningNumber();

        Output.promptBonusNumber();
        this.bonusNumber = Input.inputBonusNumber(winningNumbers);

        Map<Rank, Integer> statistics = calculateStatistics(winningNumbers, bonusNumber);

        Output.displayWinnerStatistics(statistics);
    }

    private void issueLotto(Integer purchaseAmount) {
        for (int i = 0; i < purchaseAmount/1000; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            lottos.add(new Lotto(numbers));
        }
    }

    public Map<Rank, Integer> calculateStatistics(List<Integer> winningNumbers, int bonusNumber) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        for (Lotto lotto : this.lottos) {
            List<Integer> myNumbers = lotto.getNumbers();

            int matchCount = (int) myNumbers.stream()
                    .filter(winningNumbers::contains)
                    .count();

            Rank rank = Rank.valueOf(matchCount, isBonusMatch(myNumbers, bonusNumber));
            statistics.put(rank, statistics.get(rank) + 1);
        }
        return statistics;
    }

    private Long calculateTotalPrize(Map<Rank, Integer> statistics) {
        long totalPrize = 0;

        for (Rank rank : statistics.keySet()) {
            long prize = rank.getPrizeMoney();
            int count = statistics.get(rank);
            totalPrize += (prize * count);
        }

        return totalPrize;
    }

    private boolean isBonusMatch(List<Integer> myNumbers, Integer bonusNumber) {
        return myNumbers.contains(bonusNumber);
    }
}
