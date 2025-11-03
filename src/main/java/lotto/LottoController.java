package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoController {
    private Integer purchaseAmount = 0;
    private List<Integer> winningNumbers = new ArrayList<>();
    private Integer bonusNumber = 0;
    private List<Lotto> lottos = new ArrayList<>();

    public void purchaseLotto() {
        Output.promptCost();
        this.purchaseAmount = Input.inputPurchaseAmount();
        issueLotto(this.purchaseAmount);
        Output.displayLottos(lottos);;
    }

    private void issueLotto(Integer purchaseAmount) {
        for (int i = 0; i < purchaseAmount/1000; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            lottos.add(new Lotto(numbers));
        }
    }
}
