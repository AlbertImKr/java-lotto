package lotto;

import java.util.Optional;
import java.util.Scanner;

public class CommandLineView {

	private CommandLineView() {

	}

	public static Optional<Money> inputMoney() {
		try {
			System.out.println("로또 구입 금액을 1,000원 단위로 입력하세요.");
			Scanner scanner = new Scanner(System.in);
			String moneyString = scanner.nextLine();
			return Optional.of(new Money(moneyString));
		} catch (IllegalArgumentException e) {
			System.err.println("잘못 입력했습니다. 로또 구입 금액을 1,000원 단위로 입력하세요.");
			return Optional.empty();
		}
	}
}
