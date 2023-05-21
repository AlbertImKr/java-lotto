package lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

	public static final String TYPE_ERROR = "잘못된 당첨번호 타입입니다.";

	public static final int START_INDEX = 1;
	public static final int TO_INDEX = 7;
	public static final int GENERATE_START_EXCLUSIVE = 1;
	public static final int GENERATE_END_EXCLUSIVE = 46;

	private final static List<LottoNumber> allLottoNumbers =
		IntStream.range(GENERATE_START_EXCLUSIVE, GENERATE_END_EXCLUSIVE)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toList());
	public static final String TYPE_REGEX = "^[1-9]?[0-9]*(,[1-9]?[0-9]){5}";
	public static final String WINING_NUMBERS_DELIMITER = ",";

	private LottoMachine() {

	}

	public static Lotto generateRandomTicket() {
		Collections.shuffle(allLottoNumbers);
		List<LottoNumber> lottoNumbers = allLottoNumbers.subList(START_INDEX, TO_INDEX);
		lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getLottoNumber));
		return new Lotto(new HashSet<>(lottoNumbers));
	}

	public static Lotto generateByString(String winningNumbersString) {
		validateType(winningNumbersString);
		Set<LottoNumber> winningNumbers = Arrays.stream(winningNumbersString.split(WINING_NUMBERS_DELIMITER))
			.map(s -> new LottoNumber(Integer.parseInt(s)))
			.collect(Collectors.toSet());
		return new Lotto(winningNumbers);
	}

	private static void validateType(String winningNumbersString) {
		if (!winningNumbersString.matches(TYPE_REGEX)) {
			throw new IllegalArgumentException(TYPE_ERROR);
		}
	}
}
