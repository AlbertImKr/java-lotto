package lotto;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbers {
	public static final String TYPE_ERROR = "잘못된 당첨번호 타입입니다.";
	public static final String SIZE_ERROR = "당첨번호는 서로 다르고 6개 이여야 합니다.";
	private final Set<LottoNumber> winningNumbers;

	private WinningNumbers(Set<LottoNumber> winningNumbers) {
		validateSize(winningNumbers);
		this.winningNumbers = winningNumbers;
	}

	private void validateSize(Set<LottoNumber> winningNumbers) {
		if (winningNumbers.size() != 6) {
			throw new IllegalArgumentException(SIZE_ERROR);
		}
	}

	public static WinningNumbers generateByString(String winningNumbersString) {
		validateType(winningNumbersString);
		Set<LottoNumber> winningNumbers = Arrays.stream(winningNumbersString.split(","))
			.map(s -> new LottoNumber(Integer.parseInt(s)))
			.collect(Collectors.toSet());
		return new WinningNumbers(winningNumbers);
	}

	private static void validateType(String winningNumbersString) {
		if (!winningNumbersString.matches("^[1-9]?[0-9]*(,[1-9]?[0-9]){5}")) {
			throw new IllegalArgumentException(TYPE_ERROR);
		}
	}
}
