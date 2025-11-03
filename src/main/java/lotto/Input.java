package lotto;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Console;

public class Input {
    public static Integer inputPurchaseAmount() {
        while(true) {
            try {
                String input = Console.readLine();

                validateNumber(input);
                validatePurchaseAmount(Integer.parseInt(input));
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " 다시 입력해 주세요.");
            }
        }
    }

    public static List<Integer> inputWinningNumber() {
        while(true) {
            try {
                String input = Console.readLine();

                List<Integer> winningNumbers = parseWinningNumbers(input);
                validateWinningNumbers(winningNumbers);
                return winningNumbers;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " 다시 입력해 주세요.");
            }
        }
    }

    public static Integer inputBonusNumber(List<Integer> winningNumbers) {
        while(true) {
            try {
                String input = Console.readLine();
                validateNumber(input);
                validateBonusNumber(winningNumbers, Integer.parseInt(input));
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " 다시 입력해 주세요.");
            }
        }
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

    private static List<Integer> parseWinningNumbers(String input) {
        String[] stringNumbers = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String number : stringNumbers) {
            validateNumber(number.trim());
            numbers.add(Integer.parseInt(number.trim()));
        }
        return numbers;
    }

    public static void validateWinningNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }

        long uniqueCount = numbers.stream().distinct().count();
        if (uniqueCount != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }

        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    public static void validateBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 번호 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        for (Integer number : winningNumbers) {
            if (bonusNumber.equals(number)) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
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
