package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputTest {
    @Test
    @DisplayName("정수가 아닌 값이 입력되면 예외처리")
    void validNotInteger() {
        String input = "팔천원";

        assertThatThrownBy(() -> Input.validateNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자를 입력해야 합니다.");
    }

    @Test
    @DisplayName("입력된 값이 1000으로 나누어 떨어지지 않는 경우 예외처리")
    void validNotMultipleOf1000() {
        String input = "8001";

        assertThatThrownBy(() -> Input.validatePurchaseAmount(Integer.parseInt(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액이 1000원으로 나누어 떨어지지 않습니다.");
    }

    @Test
    @DisplayName("입력된 값이 0보다 작거나 같은 경우 예외처리")
    void validZeroOrLess() {
        String input = "-1000";

        assertThatThrownBy(() -> Input.validatePurchaseAmount(Integer.parseInt(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("입력된 당첨 번호 예외처리 테스트")
    void validWinningNumber() {
        List<Integer> winningNumber1 = List.of(1,2,3,4,5,6,7);
        List<Integer> winningNumber2 = List.of(1,1,1,1,1,1);
        List<Integer> winningNumber3 = List.of(0,55,51,52,58,61);

        assertThatThrownBy(() -> Input.validateWinningNumbers(winningNumber1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 6개여야 합니다.");

        assertThatThrownBy(() -> Input.validateWinningNumbers(winningNumber2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 중복될 수 없습니다.");

        assertThatThrownBy(() -> Input.validateWinningNumbers(winningNumber3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
    }
}
