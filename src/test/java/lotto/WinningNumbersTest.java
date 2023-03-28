package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumbersTest {

	@DisplayName("입력 타입 잘못 돼여 있을 때 오류 발생")
	@ParameterizedTest
	@ValueSource(strings = {"12,aa,f,a,a,a", "22,2,3,4,5", "1,2,3,4,5,6,7"})
	void generateByStringFailedAndThrowTypeError(String winningNumbersString) {
		assertThatThrownBy(() -> WinningNumbers.generateByString(winningNumbersString))
			.isInstanceOf(IllegalArgumentException.class).hasMessage(WinningNumbers.TYPE_ERROR);
	}

	@DisplayName("입력 문자 중복이 있을 때 오류 발생")
	@ParameterizedTest
	@ValueSource(strings = {"2,2,3,4,5,6", "3,2,1,3,4,5"})
	void generateByStringFailedAndThrowSizeError(String winningNumbersString) {
		assertThatThrownBy(() -> WinningNumbers.generateByString(winningNumbersString))
			.isInstanceOf(IllegalArgumentException.class).hasMessage(WinningNumbers.SIZE_ERROR);
	}

	@DisplayName("정확히 입력시 객체 생성")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,6", "2,21,31,40,42,45"})
	void generateByStringSuccess(String winningNumbersString) {
		WinningNumbers result = WinningNumbers.generateByString(winningNumbersString);
		assertThat(result).isNotNull();
	}
}
