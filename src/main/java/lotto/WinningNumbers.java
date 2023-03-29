package lotto;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbers extends Lotto {
	public static final String TYPE_ERROR = "잘못된 당첨번호 타입입니다.";

	public WinningNumbers(Set<LottoNumber> lottoNumbers) {
		super(lottoNumbers);
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
