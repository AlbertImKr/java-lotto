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

	public static Optional<WinningNumbers> inputWinningNumbers() {
		try {
			System.out.println("당첨 번호를 입력 하세요. 번호는 쉼표(,)를 기준으로 구분해주세요.");
			Scanner scanner = new Scanner(System.in);
			String winningNumbers = scanner.nextLine();
			return Optional.of(WinningNumbers.generateByString(winningNumbers));
		} catch (IllegalArgumentException e) {
			System.err.println("잘못 입력했습니다. 번호는 쉼표(,)를 기준으로 구분해주세요.");
			return Optional.empty();
		}
	}

	public static Optional<LottoNumber> inputBonusNumber() {
		try {
			System.out.println("보너스 번호를 입력하세요.");
			Scanner scanner = new Scanner(System.in);
			String bonusNumberString = scanner.nextLine();
			return Optional.of(LottoNumber.generateByString(bonusNumberString));
		} catch (IllegalArgumentException e) {
			System.err.println("잘못 입력했습니다.");
			return Optional.empty();
		}
	}
}
