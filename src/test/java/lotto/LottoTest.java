package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

	@DisplayName("로또 번호 6개가 아니면 오류를 발생한다")
	@ParameterizedTest
	@ValueSource(ints = {5, 7})
	void generateFailed(int size) {
		Set<LottoNumber> lottoNumbers = IntStream.range(1, size + 1)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toSet());
		assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Lotto.SIZE_ERROR);
	}

	@DisplayName("로또 번호 6개이면 정상으로 생성한다")
	@ParameterizedTest
	@ValueSource(ints = {6})
	void generateSuccess(int size) {
		Set<LottoNumber> lottoNumbers = IntStream.range(1, size + 1)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toSet());
		assertThat(new Lotto(lottoNumbers)).isNotNull();
	}
}
