package lotto;

import java.util.List;
import camp.nextstep.edu.missionutils.Console;

public class Input {
    public static Integer inputPurchaseAmount() {
        while (true) {
            String input = Console.readLine();

            try {
                validateNumber(input);
                validatePurchaseAmount(Integer.parseInt(input));
                return Integer.parseInt(input);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void inputWinningNumber() {
    }

    public static void inputBonusNumber() {

    }

    public static void separateWinningNumber() {

    }

    public static void validateNumber(String input) {
        if (!isInteger(input)) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해야 합니다.");
        }
    }

    public static void validatePurchaseAmount(Integer input) {
        if (input % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액이 1000원으로 나누어 떨어지지 않습니다.");
        }

        if (input <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
        }
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
