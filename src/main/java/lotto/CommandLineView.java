package lotto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandLineView {

	public static final String INPUT_MONEY_DESCRIPTION = "로또 구입 금액을 1,000원 단위로 입력하세요.";
	public static final String INPUT_MONEY_ERROR = "잘못 입력했습니다. 로또 구입 금액을 1,000원 단위로 입력하세요.";
	public static final String INPUT_WINNING_NUMBERS_DESCRIPTION = "당첨 번호를 입력 하세요. 번호는 쉼표(,)를 기준으로 구분해주세요.";
	public static final String INPUT_WINNING_NUMBERS_ERROR = "잘못 입력했습니다. 번호는 쉼표(,)를 기준으로 구분해주세요.";
	public static final String INPUT_BONUS_NUMBER_DESCRIPTION = "보너스 번호를 입력하세요.";
	public static final String INPUT_BONUS_NUMBER_ERROR = "잘못 입력했습니다.";

	private CommandLineView() {

	}

	public static Optional<Money> inputMoney() {
		try {
			System.out.println(INPUT_MONEY_DESCRIPTION);
			Scanner scanner = new Scanner(System.in);
			String moneyString = scanner.nextLine();
			return Optional.of(new Money(moneyString));
		} catch (IllegalArgumentException e) {
			System.err.println(INPUT_MONEY_ERROR);
			return Optional.empty();
		}
	}

	public static Optional<Lotto> inputWinningNumbers() {
		try {
			System.out.println(INPUT_WINNING_NUMBERS_DESCRIPTION);
			Scanner scanner = new Scanner(System.in);
			String winningNumbers = scanner.nextLine();
			return Optional.of(LottoMachine.generateByString(winningNumbers));
		} catch (IllegalArgumentException e) {
			System.err.println(INPUT_WINNING_NUMBERS_ERROR);
			return Optional.empty();
		}
	}

	public static Optional<LottoNumber> inputBonusNumber() {
		try {
			System.out.println(INPUT_BONUS_NUMBER_DESCRIPTION);
			Scanner scanner = new Scanner(System.in);
			String bonusNumberString = scanner.nextLine();
			return Optional.of(LottoNumber.generateByString(bonusNumberString));
		} catch (IllegalArgumentException e) {
			System.err.println(INPUT_BONUS_NUMBER_ERROR);
			return Optional.empty();
		}
	}

	public static void printTickets(List<Lotto> tickets) {
		System.out.println(tickets.size() + "개를 구매했습니다.");
		for (Lotto lotto : tickets) {
			System.out.println(lotto.getLottoNumber());
		}
	}

	public static void printResult(Map<Prize, Long> result) {
		List<Prize> collect = Arrays.stream(Prize.values())
			.filter(prize -> prize != Prize.NO)
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList());

		for (Prize prize : collect) {
			Long quantity = result.getOrDefault(prize, 0L);
			System.out.println(prize.getDescription() + " - " + quantity + "개");
		}
	}

	public static void printWiningRate(String rate) {
		System.out.println("총 수익률은 " + rate + "%입니다.");
	}
}
