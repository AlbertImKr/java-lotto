package lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

	public static final String TYPE_ERROR = "잘못된 당첨번호 타입입니다.";

	private final static List<LottoNumber> allLottoNumbers = IntStream.range(1, 46)
		.mapToObj(LottoNumber::new)
		.collect(Collectors.toList());

	private LottoMachine() {

	}

	public static Lotto generateRandomTicket() {
		Collections.shuffle(allLottoNumbers);
		List<LottoNumber> lottoNumbers = allLottoNumbers.subList(1, 6);
		lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getLottoNumber));
		return new Lotto(lottoNumbers.stream().collect(Collectors.toSet()));
	}

	public static Lotto generateByString(String winningNumbersString) {
		validateType(winningNumbersString);
		Set<LottoNumber> winningNumbers = Arrays.stream(winningNumbersString.split(","))
			.map(s -> new LottoNumber(Integer.parseInt(s)))
			.collect(Collectors.toSet());
		return new Lotto(winningNumbers);
	}

	private static void validateType(String winningNumbersString) {
		if (!winningNumbersString.matches("^[1-9]?[0-9]*(,[1-9]?[0-9]){5}")) {
			throw new IllegalArgumentException(TYPE_ERROR);
		}
	}
}
