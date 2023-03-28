package lotto;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandLineViewTest {

	@DisplayName("잘못된 금액 문자 일 때 Optional.empty() 반환한다.")
	@ParameterizedTest
	@ValueSource(strings = {"100", "900", "1210", "aaaa", "11aaa", "ac1111"})
	void inputMoneyFailed(String moneyString) {
		InputStream inputStream = new ByteArrayInputStream(moneyString.getBytes());
		System.setIn(inputStream);
		assertThat(CommandLineView.inputMoney()).isEmpty();
	}

	@DisplayName("정확한 금액 문자 일 때 객체를 Optional 에 담아 반환한다.")
	@ParameterizedTest
	@ValueSource(strings = {"1000", "9000", "121000"})
	void inputMoneySuccess(String moneyString) {
		InputStream inputStream = new ByteArrayInputStream(moneyString.getBytes());
		System.setIn(inputStream);
		assertThat(CommandLineView.inputMoney()).isPresent();
	}

	@DisplayName("잘못된 당첨번호 문자 일 때 Optional.empty() 반환한다.")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5", "1;2;3,4,5,6", "1,1,2,3,4,5", "1,2,3,4,5,6,7", "11aaa", "ac1111"})
	void inputWinningNumberFailed(String moneyString) {
		InputStream inputStream = new ByteArrayInputStream(moneyString.getBytes());
		System.setIn(inputStream);
		assertThat(CommandLineView.inputWinningNumbers()).isEmpty();
	}

	@DisplayName("정확한 당첨번호 문자 일 때 객체를 Optional 에 담아 반환한다.")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,6", "1,21,31,32,44,45", "4,2,6,9,7,8"})
	void inputWinningNumberSuccess(String moneyString) {
		InputStream inputStream = new ByteArrayInputStream(moneyString.getBytes());
		System.setIn(inputStream);
		assertThat(CommandLineView.inputWinningNumbers()).isPresent();
	}
}
