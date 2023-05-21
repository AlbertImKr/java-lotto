package lotto;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoCheckerTest {

	@DisplayName("로또 당첨 결과 체크")
	@Test
	void getResult() {
		Lotto lotto1To6 = getLottoNumbersSet(1, 7);
		Lotto lotto2To7 = getLottoNumbersSet(2, 8);
		Lotto lotto3To8 = getLottoNumbersSet(3, 9);

		Set<LottoNumber> lottoNumbers2To6Add8 = IntStream.range(2, 7)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toSet());
		lottoNumbers2To6Add8.add(new LottoNumber(8));
		Lotto lotto2To6Add8 = new Lotto(lottoNumbers2To6Add8);

		Map<Prize, Long> result = LottoChecker.getResult(lotto1To6, new LottoNumber(7),
			List.of(lotto1To6, lotto2To7, lotto2To7, lotto3To8, lotto2To6Add8));
		Assertions.assertThat(result)
			.containsEntry(Prize.FIRST, 1L)
			.containsEntry(Prize.BONUS, 2L)
			.containsEntry(Prize.SECOND, 1L)
			.containsEntry(Prize.THIRD, 1L);
	}

	private static Lotto getLottoNumbersSet(int startInclusive, int endExclusive) {
		return new Lotto(IntStream.range(startInclusive, endExclusive)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toSet()));
	}
}
