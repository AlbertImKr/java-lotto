package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

	@DisplayName("로또 넘버 범위를 초과하면 오류가 발생한다.")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void generateFailed(int lottoNumber) {
		assertThatThrownBy(() -> new LottoNumber(lottoNumber)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage(LottoNumber.RANGE_ERROR);
	}

	@DisplayName("로또 넘버 범위 내이면 정상으로 생성한다.")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 45, 44})
	void generateSuccess(int lottoNumber) {
		assertThat(new LottoNumber(lottoNumber)).hasFieldOrPropertyWithValue("lottoNumber", lottoNumber);
	}

	@DisplayName("로또 넘버 문자를 잘못입력하면 오류가 발생한다.")
	@ParameterizedTest
	@ValueSource(strings = {"0","46","string","8a"})
	void generateByStringFailed(String lottoNumberString) {
		assertThatThrownBy(() -> LottoNumber.generateByString(lottoNumberString)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@DisplayName("로또 넘버 문자를 정확히 입력하면 정상을 생성한다.")
	@ParameterizedTest
	@ValueSource(strings = {"1","45","20"})
	void generateByStringSuccess(String lottoNumberString) {
		assertThat(LottoNumber.generateByString(lottoNumberString)).hasFieldOrPropertyWithValue("lottoNumber",
			Integer.parseInt(lottoNumberString));
	}
}
