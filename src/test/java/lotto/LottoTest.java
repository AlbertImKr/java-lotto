package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

	@DisplayName("로또 번호 6개가 아니면 오류를 발생한다")
	@ParameterizedTest
	@ValueSource(ints = {5, 7})
	void generateFailed(int size) {
		Set<LottoNumber> lottoNumbers = getLottNumbers(size);
		assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Lotto.SIZE_ERROR);
	}

	@DisplayName("로또 번호 6개이면 정상으로 생성한다")
	@ParameterizedTest
	@ValueSource(ints = {6})
	void generateSuccess(int size) {
		Set<LottoNumber> lottoNumbers = getLottNumbers(size);
		assertThat(new Lotto(lottoNumbers)).isNotNull();
	}

	@DisplayName("정렬된 로또 번호 return 한다")
	@ParameterizedTest
	@CsvSource({"1,7", "5,11", "40,46"})
	void getLottoNumber(int startInclusive, int endExclusive) {
		Lotto lotto = getLottoNumbersSet(startInclusive, endExclusive);
		assertThat(lotto.getLottoNumber()).isEqualTo(
			IntStream.range(startInclusive, endExclusive)
				.boxed()
				.collect(Collectors.toList()).toString()
		);
	}

	@DisplayName("로또 번호 포함 여부를 확인한다")
	@Test
	void contains() {
		Lotto lotto = getLottoNumbersSet(1, 7);
		assertThat(lotto.contains(new LottoNumber(1))).isTrue();
		assertThat(lotto.contains(new LottoNumber(7))).isFalse();
	}

	@DisplayName("두 로또 번호의 일치 개수를 return")
	@Test
	void getMatchCount() {
		Lotto lotto1To6 = getLottoNumbersSet(1, 7);
		Lotto lotto2To7 = getLottoNumbersSet(2, 8);
		Lotto lotto3To8 = getLottoNumbersSet(3, 9);

		assertThat(lotto1To6.getMatchCount(lotto2To7)).isEqualTo(5);
		assertThat(lotto1To6.getMatchCount(lotto3To8)).isEqualTo(4);
		assertThat(lotto2To7.getMatchCount(lotto3To8)).isEqualTo(5);
	}

	private static Lotto getLottoNumbersSet(int startInclusive, int endExclusive) {
		return new Lotto(IntStream.range(startInclusive, endExclusive)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toSet()));
	}

	private static Set<LottoNumber> getLottNumbers(int size) {
		return IntStream.range(1, size + 1)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toSet());
	}
}
